package com.miam.cloudApi.security.service;

import com.miam.cloudApi.miamCloudApi.domain.entities.Account;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.AccountRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.RoleRepository;
import com.miam.cloudApi.miamCloudApi.infraestructure.repositories.SubscriptionRepository;
import com.miam.cloudApi.security.jwt.provider.JwtTokenProvider;
import com.miam.cloudApi.security.model.request.LoginRequestDto;
import com.miam.cloudApi.security.model.request.RegisterRequestDto;
import com.miam.cloudApi.security.model.response.RegisteredUserResponseDto;
import com.miam.cloudApi.security.model.response.TokenResponseDto;
import com.miam.cloudApi.security.storage.JwtTokenStorage;
import com.miam.cloudApi.shared.exception.ValidationException;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;
import com.miam.cloudApi.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;
    private final JwtTokenStorage jwtTokenStorage;

    public AuthServiceImpl(AuthenticationManager authenticationManager, AccountRepository accountRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper, SubscriptionRepository subscriptionRepository, JwtTokenStorage jwtTokenStorage) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.jwtTokenStorage = jwtTokenStorage;
    }

    @Override
    public ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request) {
        //si el email ya está registrado
        validateAccount(request);

        //si no existe, lo registra
        var account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .isActive(request.isActive())
                .createdAt(LocalDateTime.now())
                .role(roleRepository.getRoleById(request.getRole()))
                .subscription(subscriptionRepository.getSubscriptionById(request.getSubscription()))
                .build();

        //guarda el usuario
        var newUser = accountRepository.save(account);

        //mapea de la entidad al dto
        var responseData = modelMapper.map(newUser, RegisteredUserResponseDto.class);

        return new ApiResponse<>("Register Success", Estatus.SUCCESS, responseData);
    }

    @Override
    public ApiResponse<TokenResponseDto> login(LoginRequestDto request) {



        //se validan las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //establece la seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //se obtiene el token
        String token = jwtTokenProvider.generateToken(authentication);

        //se obtiene usuario autenticado
        Optional<Account> account = accountRepository.findAccountByEmail(request.getEmail());

        jwtTokenStorage.setToken(token);

        //var responseData = new TokenResponseDto(token);
        TokenResponseDto responseData = TokenResponseDto.builder()
                .id(account.get().getId())
                .token(token)
                .build();

        return new ApiResponse<>("Authentication Success", Estatus.SUCCESS, responseData);
    }


    private void validateAccount(RegisterRequestDto request) {
        if (!isValidateEmail(request.getEmail())) {
            throw new ValidationException("The email provided is not valid");
        }
        if (!isPasswordStrong(request.getPassword())) {
            throw new ValidationException("The password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and no spaces");
        }
        if (!isValidRole(request.getRole())) {
            throw new ValidationException("The role provided is not valid");
        }
        if (isEmailExists(request.getEmail())) {
            throw new ValidationException("The email provided is already registered");
        }
    }


    private boolean isValidateGender(String gender) {
        return gender.equals("Femenino") || gender.equals("Masculino") || gender.equals("Otro") || gender.equals("Prefiero no decirlo");
    }
    /*
    private  boolean isValidateEmail(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    */

    private boolean isValidateEmail(String email) {
        // Expresión regular que valida las cuentas de Gmail, Outlook y Hotmail
        String regex = "^[\\w-\\.]+@(gmail\\.com|outlook\\.(com|es)|hotmail\\.com)$";
        return email.matches(regex);
    }

    private boolean isAdult(LocalDate birthday) {
        return birthday.plusYears(18).isBefore(LocalDate.now());
    }

    private boolean isPasswordStrong(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
    }
    private boolean isValidRole(int roleId) {
        return roleId == 1 || roleId == 2;
    }

    private boolean isEmailExists(String email) {
        return accountRepository.existsAccountByEmail(email);
    }



}

package com.miam.cloudApi.miamCloudApi.application.services;

import com.miam.cloudApi.miamCloudApi.application.dto.request.MedicationScheduleRequest;
import com.miam.cloudApi.miamCloudApi.application.dto.request.UpdateMedicationScheduleTaken;
import com.miam.cloudApi.miamCloudApi.application.dto.response.MedicationScheduleResponse;
import com.miam.cloudApi.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface MedicationScheduleService {

    ApiResponse<MedicationScheduleResponse> getMedicationScheduleById(int id);

    ApiResponse<List<MedicationScheduleResponse>> getMedicationScheduleByPatientId(int patientId);

    ApiResponse<MedicationScheduleResponse> createMedicationSchedule(MedicationScheduleRequest medicationScheduleRequest);

    ApiResponse<MedicationScheduleResponse> updateMedicationSchedule(int id, UpdateMedicationScheduleTaken updateMedicationScheduleTaken);

    ApiResponse<Void> deleteMedicationSchedule(int id);

}

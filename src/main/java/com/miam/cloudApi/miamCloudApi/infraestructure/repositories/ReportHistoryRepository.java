package com.miam.cloudApi.miamCloudApi.infraestructure.repositories;

import com.miam.cloudApi.miamCloudApi.domain.entities.ReportHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportHistoryRepository extends CrudRepository<ReportHistory, Integer> {

    List<ReportHistory> getReportHistoryByCaregiverId(int id);

    List<ReportHistory> getReportHistoryByPatientId(int id);

}

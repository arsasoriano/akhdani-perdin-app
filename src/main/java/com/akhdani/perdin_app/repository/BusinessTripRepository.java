package com.akhdani.perdin_app.repository;

import com.akhdani.perdin_app.entity.BusinessTrip;
import com.akhdani.perdin_app.entity.User;
import com.akhdani.perdin_app.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Long> {
    List<BusinessTrip> findByUser(User user);
    List<BusinessTrip> findByStatus(ApprovalStatus status);
}

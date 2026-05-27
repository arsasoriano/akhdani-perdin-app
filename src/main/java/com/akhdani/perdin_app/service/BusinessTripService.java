package com.akhdani.perdin_app.service;

import com.akhdani.perdin_app.dto.BusinessTripRequest;
import com.akhdani.perdin_app.entity.BusinessTrip;

import java.util.List;

public interface BusinessTripService {
    void submitTrip(BusinessTripRequest request, String username);

    List<BusinessTrip> getMyTrip(String username);

    List<BusinessTrip> getAllTrip();

    void approveTrip(Long id);

    void rejectTrip(Long id);
}

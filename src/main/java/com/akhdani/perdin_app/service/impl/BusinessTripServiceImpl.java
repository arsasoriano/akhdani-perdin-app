package com.akhdani.perdin_app.service.impl;

import com.akhdani.perdin_app.dto.BusinessTripRequest;
import com.akhdani.perdin_app.entity.BusinessTrip;
import com.akhdani.perdin_app.entity.City;
import com.akhdani.perdin_app.entity.User;
import com.akhdani.perdin_app.enums.ApprovalStatus;
import com.akhdani.perdin_app.repository.BusinessTripRepository;
import com.akhdani.perdin_app.repository.CityRepository;
import com.akhdani.perdin_app.repository.UserRepository;
import com.akhdani.perdin_app.service.BusinessTripService;
import com.akhdani.perdin_app.util.DistanceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessTripServiceImpl implements BusinessTripService {
    private final BusinessTripRepository businessTripRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;

    @Override
    public void submitTrip(BusinessTripRequest request, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        City origin = cityRepository.findById(request.getOriginCityId()).orElseThrow(RuntimeException::new);
        City destination = cityRepository.findById(request.getDestinationCityId()).orElseThrow(RuntimeException::new);

        if (request.getReturnDate().isBefore(request.getDepartureDate())) {
            throw new RuntimeException("Return date cannot be before departure date");
        }

        if (request.getOriginCityId().equals(request.getDestinationCityId())) {
            throw new RuntimeException("Origin city and destination cannot be same");
        }

        long duration = ChronoUnit.DAYS.between(request.getDepartureDate(), request.getReturnDate()) + 1;

        double distance = DistanceUtil.calculateDistance(
                origin.getLatitude(),
                origin.getLongitude(),
                destination.getLatitude(),
                destination.getLongitude()
        );

        BigDecimal dailyAllowance = calculateAllowance(distance, origin, destination);

        BigDecimal total = dailyAllowance.multiply(BigDecimal.valueOf(duration));

        BusinessTrip trip = BusinessTrip.builder()
                .purpose(request.getPurpose())
                .departureDate(request.getDepartureDate())
                .returnDate(request.getReturnDate())
                .duration((int) duration)
                .allowance(total)
                .status(ApprovalStatus.PENDING)
                .originCity(origin)
                .destinationCity(destination)
                .user(user)
                .build();
        businessTripRepository.save(trip);
    }

    @Override
    public List<BusinessTrip> getMyTrip(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        return businessTripRepository.findByUser(user);
    }

    @Override
    public List<BusinessTrip> getAllTrip() {
        return businessTripRepository.findAll();
    }

    @Override
    public void approveTrip(Long id) {
        BusinessTrip businessTrip = businessTripRepository.findById(id).orElseThrow(RuntimeException::new);
        businessTrip.setStatus(ApprovalStatus.APPROVED);
        businessTripRepository.save(businessTrip);
    }

    @Override
    public void rejectTrip(Long id) {
        BusinessTrip businessTrip = businessTripRepository.findById(id).orElseThrow(RuntimeException::new);
        businessTrip.setStatus(ApprovalStatus.REJECTED);
        businessTripRepository.save(businessTrip);
    }

    private BigDecimal calculateAllowance(double distance, City origin, City destination) {
        if (destination.getLuarNegeri()) {
            return BigDecimal.valueOf(50);
        }
        if (distance <= 60) {
            return BigDecimal.ZERO;
        }
        if (origin.getProvince().equalsIgnoreCase(destination.getProvince())) {
            return BigDecimal.valueOf(200000);
        }
        if (origin.getIsland().equalsIgnoreCase(destination.getIsland())) {
            return BigDecimal.valueOf(250000);
        }
        return BigDecimal.valueOf(300000);
    }
}

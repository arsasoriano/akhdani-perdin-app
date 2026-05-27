package com.akhdani.perdin_app.entity;

import com.akhdani.perdin_app.enums.ApprovalStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "BUSINESS_TRIP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_trip_seq_generator")
    @SequenceGenerator(name = "business_trip_generator", sequenceName = "BUSINESS_TRIP_SEQ", allocationSize = 1)
    private Long id;
    private String purpose;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer duration;
    private BigDecimal allowance;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @ManyToOne
    @JoinColumn(name = "ORIGIN_CITY_ID")
    private City originCity;

    @ManyToOne
    @JoinColumn(name = "DESTINATION_CITY_ID")
    private City destinationCity;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}

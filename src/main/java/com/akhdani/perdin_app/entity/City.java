package com.akhdani.perdin_app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CITY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq_generator")
    @SequenceGenerator(name = "city_seq_generator", sequenceName = "CITY_SEQ", allocationSize = 1)
    private Long id;
    private String cityName;
    private Double latitude;
    private Double longitude;
    private String province;
    private String island;
    private Boolean luarNegeri;
}

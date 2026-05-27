package com.akhdani.perdin_app.entity;

import com.akhdani.perdin_app.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
    @SequenceGenerator(name = "user_seq_generator", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}

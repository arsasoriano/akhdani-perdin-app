package com.akhdani.perdin_app.config;

import com.akhdani.perdin_app.entity.User;
import com.akhdani.perdin_app.enums.Role;
import com.akhdani.perdin_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            userRepository.save(
                    User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin123"))
                            .role(Role.ADMIN)
                            .build()
            );

            userRepository.save(
                    User.builder()
                            .username("pegawai")
                            .password(passwordEncoder.encode("pegawai123"))
                            .role(Role.PEGAWAI)
                            .build()
            );

            userRepository.save(
                    User.builder()
                            .username("sdm")
                            .password(passwordEncoder.encode("sdm123"))
                            .role(Role.DIVISI_SDM)
                            .build()
            );
        }
    }
}

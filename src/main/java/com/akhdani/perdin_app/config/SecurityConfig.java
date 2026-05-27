package com.akhdani.perdin_app.config;

import com.akhdani.perdin_app.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login","/css/**")
                .permitAll()

                .antMatchers("/admin/**")
                .hasRole("ADMIN")

                .antMatchers("/pegawai/**")
                .hasRole("PEGAWAI")

                .antMatchers("/sdm/**")
                .hasRole("DIVISI_SDM")

                .antMatchers("/error/**")
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .successHandler(
                        (request, response, authentication) -> {
                            String role = authentication
                                    .getAuthorities()
                                    .iterator()
                                    .next()
                                    .getAuthority();

                            if (role.equals("ROLE_ADMIN")) {
                                response.sendRedirect("/admin/dashboard");
                            }
                            else if (role.equals("ROLE_PEGAWAI")) {
                                response.sendRedirect("/pegawai/dashboard");
                            }
                            else {
                                response.sendRedirect("/sdm/dashboard");
                            }
                        }
                )
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}

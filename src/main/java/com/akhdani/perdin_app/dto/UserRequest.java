package com.akhdani.perdin_app.dto;

import com.akhdani.perdin_app.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
}

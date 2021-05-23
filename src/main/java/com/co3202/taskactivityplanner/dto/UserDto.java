package com.co3202.taskactivityplanner.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    @NotNull
    @NotEmpty
    private String email;

    @NotNull()
    @NotEmpty()
    private String password;
    private String matchingPassword;

    @NotNull()
    @NotEmpty()
    private String firstname;

    @NotNull()
    @NotEmpty()
    private String lastname;
}

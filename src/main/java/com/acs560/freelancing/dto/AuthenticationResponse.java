package com.acs560.freelancing.dto;

import com.acs560.freelancing.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for holding JWT token after successful authentication.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String jwtToken;
    private User.Role role;

}


package com.sunny.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}

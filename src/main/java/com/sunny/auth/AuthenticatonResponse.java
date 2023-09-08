package com.sunny.auth;

import com.sunny.customer.CustomerDTO;

public record AuthenticatonResponse (
        String token,
        CustomerDTO customerDTO
){

}

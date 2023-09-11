package com.sunny.customer;

import java.util.List;

public record CustomerDTO(
        Integer id,
        String name,
        String email,
        Integer age,
        String gender,
        String username
) {
}

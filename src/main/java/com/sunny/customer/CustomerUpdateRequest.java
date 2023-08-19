package com.sunny.customer;

public record CustomerUpdateRequest(String name,String email,Integer age,String genders) {
}
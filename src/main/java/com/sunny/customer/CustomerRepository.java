package com.sunny.customer;

import org.springframework.data.jpa.repository.JpaRepository;
//don't need to use @Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Integer id);


}

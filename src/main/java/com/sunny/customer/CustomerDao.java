package com.sunny.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
  Optional <Customer> selectUserByEmail(String email);
    List<Customer> selectAllCustomers();
     Optional<Customer> selectCustomerById(Integer Id);
     void insertCustomer(Customer customer);
     boolean existsPersonWithEmail(String email);
     void deleteCustomerById(Integer id);
     boolean existsPersonWithId(Integer id);
     void  updateCustomer(Customer update);
}

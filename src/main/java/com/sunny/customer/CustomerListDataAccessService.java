package com.sunny.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{
    private static final List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer( "Sunny", "sunny@gmail.com", 22,"Male","password"));
        customers.add(new Customer( "Alex", "Alex@ hotmail.com", 22,"women","password"));
    }

    @Override
    public void deleteCustomerById(Integer id) {

    }

    @Override
    public Optional<Customer> selectUserByEmail(String email) {
        return customers.stream().filter(c->c.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }
@Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void updateCustomer(Customer update) {
        customers.add(update);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream().anyMatch(
                customer -> customer.getId().equals(id)
        );

    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream().anyMatch(
                customer -> customer.getEmail().equals(email)
        );
    }
}

package com.sunny.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static javax.swing.text.html.parser.DTDConstants.ID;
@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao{

    private final CustomerRepository customerRepository;
    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer Id) {

        return customerRepository.findById(Id);
    }

    @Override
    public void insertCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    @Override
    public void deleteCustomerById(Integer id) {
                customerRepository.deleteById(id);
            }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return  customerRepository.existsCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer update) {
        customerRepository.save(update);
    }


            @Override
            public boolean existsPersonWithEmail(String email) {
                return  customerRepository.existsCustomerByEmail(email);

            }


        }

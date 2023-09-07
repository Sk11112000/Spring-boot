package com.sunny.customer;

import com.sunny.exception.DuplicateResourceException;
import com.sunny.exception.RequestValidationException;
import com.sunny.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFound("Customer with id [%s] not found".formatted(id)));
    }
    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest)
    {

        String email=customerRegistrationRequest.email();
        if(customerDao.existsPersonWithEmail(email))
        {
            throw new DuplicateResourceException("Email already Taken ");
        }

        customerDao.insertCustomer(
                new Customer(
                        customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age(),
                        customerRegistrationRequest.gender(),
                        passwordEncoder.encode(customerRegistrationRequest.password())
                )
        );

    }
    public void deleteCustomerById(Integer id)
    {   if(!customerDao.existsPersonWithId(id))
    {
        throw new DuplicateResourceException("Data With "+id+"not exist");
    }
        customerDao.deleteCustomerById(id);
    }
    public void updateCustomer(Integer id,CustomerUpdateRequest updateRequest)
    {
        Customer customer=getCustomer(id);
        boolean change=false;
        if(updateRequest.name()!=null && !updateRequest.name().equals(customer.getName()))
        {
            customer.setName(updateRequest.name());
            change=true;
        }
        if(updateRequest.age()!=null && updateRequest.age()!=customer.getAge())
        {
            customer.setAge(updateRequest.age());
            change=true;
        }
        if(updateRequest.email()!=null && !updateRequest.email().equals(customer.getEmail()))
        {
            if(customerDao.existsPersonWithEmail(updateRequest.email()))
            {
                throw new DuplicateResourceException(
                        "Email already Present"
                );
            }
            System.out.println(updateRequest.email());
            customer.setEmail(updateRequest.email());

            change=true;
        }
        if(updateRequest.gender() != null && !updateRequest.gender().equals(customer.getGender()))
        {
            customer.setGender(updateRequest.gender());
            change = true;
        }
        if(!change)
        {
            throw new RequestValidationException("No Data Change Found");
        }
        customerDao.updateCustomer(customer);
    }
}
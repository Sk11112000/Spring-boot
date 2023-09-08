package com.sunny.customer;

import com.sunny.exception.DuplicateResourceException;
import com.sunny.exception.RequestValidationException;
import com.sunny.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomerService(@Qualifier("jdbc") CustomerDao customerDao, PasswordEncoder passwordEncoder, CustomerDTOMapper customerDTOMapper) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
        this.customerDTOMapper = customerDTOMapper;
    }
//    Integer id,
//    String name,
//    String email,
//    Integer age,
//    String gender,
//    List<String> roles
//    ,
//    String username
    public List<CustomerDTO> getAllCustomers() {
        return customerDao.selectAllCustomers()
                .stream()
                .map(customerDTOMapper)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .map(customerDTOMapper)
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
       Customer customer= customerDao.selectCustomerById(id)
               .orElseThrow(() -> new ResourceNotFound("Customer with id [%s] not found".formatted(id)));
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
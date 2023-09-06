package com.sunny.customer;


import jwt.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    private final CustomerService customerService;
    private final JWTUtil jwtUtil;

    public CustomerController(CustomerService customerService,JWTUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil=jwtUtil;
    }

    @GetMapping("customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("customer/{customerId}")
    public Customer getCustomer(@PathVariable Integer customerId) {
        return customerService.getCustomer(customerId);
    }
    @PostMapping("add/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegistrationRequest request) {


        // Your existing code...
        String jwtToken = jwtUtil.issueToken (request.email(),"ROLE_USER");

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwtToken ).build();
    }
    @DeleteMapping("delete/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId)
    {

        customerService.deleteCustomerById(customerId);
    }
    @PutMapping("update/{customerId}")
    public void updateCustomerById(@PathVariable Integer customerId,@RequestBody CustomerUpdateRequest updateRequest)
    {
        System.out.println(updateRequest.gender());
        customerService.updateCustomer(customerId,updateRequest);
    }
}

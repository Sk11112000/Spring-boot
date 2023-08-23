package com.sunny.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request)
    {

        customerService.addCustomer(request);
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

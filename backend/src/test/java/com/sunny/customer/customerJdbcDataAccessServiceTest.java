package com.sunny.customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class customerJdbcDataAccessServiceTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private CustomerRowMapper customerRowMapper;

    private customerJdbcDataAccessService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new customerJdbcDataAccessService(jdbcTemplate, customerRowMapper);
    }

    @Test
    void testSelectAllCustomers() {
        List<Customer> expectedCustomers = new ArrayList<>();
        when(jdbcTemplate.query(anyString(), any(CustomerRowMapper.class))).thenReturn(expectedCustomers);

        List<Customer> result = customerService.selectAllCustomers();

        assertEquals(expectedCustomers, result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(CustomerRowMapper.class));
    }

    @Test
    void testSelectCustomerById() {
        Integer customerId = 1;
        Customer expectedCustomer = new Customer();
        when(jdbcTemplate.query(anyString(), any(CustomerRowMapper.class), eq(customerId)))
                .thenReturn(List.of(expectedCustomer));

        Optional<Customer> result = customerService.selectCustomerById(customerId);

        assertTrue(result.isPresent());
        assertEquals(expectedCustomer, result.get());
        verify(jdbcTemplate, times(1)).query(anyString(), any(CustomerRowMapper.class), eq(customerId));
    }

    @Test
    void testInsertCustomer() {
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customer.setAge(30);
        customer.setGender("male");
        customerService.insertCustomer(customer);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(customer.getName()), eq(customer.getEmail()), eq(customer.getAge()),eq(customer.getGender()));
    }

}

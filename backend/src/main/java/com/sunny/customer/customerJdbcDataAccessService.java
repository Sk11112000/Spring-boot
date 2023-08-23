package com.sunny.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jdbc")
public class customerJdbcDataAccessService implements CustomerDao{
    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;
    public customerJdbcDataAccessService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        var sql= """
                SELECT *FROM customer
                """;
       return jdbcTemplate.query(sql,customerRowMapper);

    }

    @Override
    public Optional<Customer> selectCustomerById(Integer Id) {
        var sql= """
                select * from customer where customer.id=?
                """;
       return jdbcTemplate.query(sql,customerRowMapper,Id).stream().findFirst();

    }

    @Override
    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer(name, email, age,gender) VALUES (?, ?, ?,?)";

        jdbcTemplate.update(sql, customer.getName(), customer.getEmail(), customer.getAge(),customer.getGender());
    }


    @Override
    public boolean existsPersonWithEmail(String email) {
       var sql="SELECT * FROM CUSTOMER WHERE email=?";
       List<Customer> customer=jdbcTemplate.query(sql,customerRowMapper,email);
       return !customer.isEmpty();
    }

    @Override
    public void deleteCustomerById(Integer id) {
        var sql="DELETE FROM customer WHERE id=?";
        jdbcTemplate.update(sql,id);

    }


    @Override
    public boolean existsPersonWithId(Integer id) {
        var sql="SELECT * FROM CUSTOMER WHERE id=?";
        List<Customer> customer=jdbcTemplate.query(sql,customerRowMapper,id);
        return !customer.isEmpty();
    }

    @Override
    public void updateCustomer(Customer update) {
        if(update.getName()!=null) {
            var sql = "UPDATE customer SET name=? WHERE id=?";
            jdbcTemplate.update(sql, update.getName(), update.getId());
        }if(update.getAge()!=null) {
            var sql = "UPDATE customer SET age=? WHERE id=?";
            jdbcTemplate.update(sql, update.getAge(), update.getId());
        }if(update.getEmail()!=null) {
            var sql = "UPDATE customer SET email=? WHERE id=?";
            jdbcTemplate.update(sql, update.getEmail(), update.getId());
        }
        if(update.getGender()!=null)
        {
            var sql = "UPDATE customer SET gender=? WHERE id=?";
            jdbcTemplate.update(sql, update.getGender(), update.getId());
        }
    }
}

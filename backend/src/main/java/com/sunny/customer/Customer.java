package com.sunny.customer;
import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "customer_email_unique",
                        columnNames = "email"
                )
        }
)
public class Customer{

    @Id
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false
    )
    private String gender;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private Integer age;
    Customer(){}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Customer(String name, String email, Integer age, String gender) {

        this.name = name;
        this.email = email;
        this.age = age;
        this.gender=gender;
    }
    public Customer(Integer id, String name, String email, Integer age,String gender) {
        this.id= Long.valueOf(id);
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender=gender;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }

    public void setId(Integer id) {
        this.id = Long.valueOf(id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;

    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return Math.toIntExact(id);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

}

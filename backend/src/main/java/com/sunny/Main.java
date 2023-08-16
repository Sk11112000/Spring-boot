package com.sunny;

import com.github.javafaker.Faker;
import com.sunny.customer.Customer;
import com.sunny.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Factory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Random;


@SpringBootApplication
public class Main {
    //database

    //    record Foo(String name){}
//    @Bean
//    public Foo getFoo(){
//        return new Foo("Bar");
//    } sunny kulshrestha
    public static void main(String[] args) {
        // never do this
//        CustomerService customerService=new CustomerService(new CustomerDataAccessSerice());
//        CustomerController customerController=new CustomerController(customerService);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRespository) {
        return args -> {
            var faker=new Faker();
            Random random=new Random();
            Customer sunny = new Customer(
                  faker.name().fullName(),
                    faker.internet().safeEmailAddress(),
                    random.nextInt(16,99)
            );
//            Customer alex = new Customer(
//                    "alex",
//                    "sunny@hotmail.com",
//                    24
//            );
//            List<Customer> customers= List.of(alex,sunny);
            //saveAll will automatical save all the data without writing sql query
            //Hibernate orm Object relation mapping allow us to map our object to sql
//            customerRespository.saveAll(customers);
            customerRespository.save(sunny);
        };
    }
}



//        String []beanDefinitionNames=applicationContext.getBeanDefinitionNames();
//        for(String beanDefinitionName:beanDefinitionNames)
//            System.out.println(beanDefinitionName);



// v1 for version 1
//GetMapping same as requestmapping
//    @RequestMapping(
//            path = "api/v1/customer",
//            method = RequestMethod.GET)
//same as

//    @GetMapping("/")
//    public GreetResponse greet(
//            @RequestParam(value = "name",required = false) String name){
//        String greet=name==null || name.isBlank()? "Hello": "Hello "+name;
//        return new GreetResponse(greet
//        , List.of("Java","SalesForce","Apex","JavaScript","SQL"),
//                new Person("Sunny Kulshrestha"));
//    }
//    record Person(String name){}
//    record GreetResponse(
//            String greet,
//            List<String> programmingLanguage,
//            Person person
//    ){}
//inner customer class

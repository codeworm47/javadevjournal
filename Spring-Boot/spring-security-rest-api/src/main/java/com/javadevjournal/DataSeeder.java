package com.javadevjournal;

import com.javadevjournal.jpa.Customer;
import com.javadevjournal.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    CustomerRepository repo;
    @Override
    public void run(String... args) throws Exception {
        Customer c = new Customer();
        c.setUserName("admin");
        c.setPassword("admin");
        repo.save(c);
        int a=  1;
    }
}

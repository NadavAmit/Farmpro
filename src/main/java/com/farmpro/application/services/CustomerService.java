package com.farmpro.application.services;

import com.farmpro.application.dto.CustomerDTO;
import com.farmpro.application.entities.Customer;
import com.farmpro.application.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){return  customerRepository.findAll();}
    public Customer getCustomerById(Long id){return customerRepository.findById(id).get();}
    public Customer createCustomer(Customer customer){return customerRepository.save(customer); }
    public Customer updateCustomer(Long id, Customer updatedCustomer){
        if(customerRepository.existsById(id)){
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer);
        }
        else{
            throw new NoSuchElementException("Customer object with ID " + id + " not found in the database");
        }
    }
    public void deleteCustomer(Long id){ customerRepository.deleteById(id);}
}

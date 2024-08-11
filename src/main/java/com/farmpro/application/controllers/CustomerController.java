package com.farmpro.application.controllers;

import com.farmpro.application.dto.CustomerDTO;
import com.farmpro.application.entities.Customer;
import com.farmpro.application.mappers.CustomerMapper;
import com.farmpro.application.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping({"","/"})
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOList = customers.stream().map(customer -> customerMapper.customerToCustomerDTO(customer))
                .toList();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") long id){
        Customer customer = customerService.getCustomerById(id);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }

    @PostMapping({"","/"})
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer){
        Customer newCustomer = customerMapper.customerDTOToCustomer(customer);
        Customer customerResult = customerService.createCustomer(newCustomer);
        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customerResult),HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO customer){
        Customer updatedCustomer = customerMapper.customerDTOToCustomer(customer);
        Customer customerResult = customerService.updateCustomer(id, updatedCustomer);
        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customerResult),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

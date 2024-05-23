package org.capgemini.blue_harvest.accountservice.service.impl;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.capgemini.blue_harvest.accountservice.dao.CustomerDao;
import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        // Initialize some sample customers for testing
        customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");

        customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.com");
    }

    @Test
    public void testGetAllCustomers() {
        // Mock the behavior of CustomerDao to return a list of customers
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);
        when(customerDao.getAllCustomers()).thenReturn(expectedCustomers);

        // Call the getAllCustomers() method of CustomerService
        List<org.capgemini.blue_harvest.accountservice.model.Customer> actualCustomers = customerService.getAllCustomers();

        // Verify that the correct list of customers is returned
        Assertions.assertEquals(expectedCustomers.size(), actualCustomers.size());
        Assertions.assertEquals(expectedCustomers.get(0).getId(), actualCustomers.get(0).getId());
        Assertions.assertEquals(expectedCustomers.get(1).getId(), actualCustomers.get(1).getId());
    }
}

package org.capgemini.blue_harvest.accountservice.repository;

import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

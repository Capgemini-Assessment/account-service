package org.capgemini.blue_harvest.accountservice.repository;

import java.util.List;

import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	List<Account> findByCustomerId(int customerId);
}

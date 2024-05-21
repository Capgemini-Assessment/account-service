package org.capgemini.blue_harvest.accountservice.repository;

import org.capgemini.blue_harvest.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

package com.insurance.capstone.dataaccess;

import com.insurance.capstone.User.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

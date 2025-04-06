package com.insurance.capstone.dataaccess;

import com.insurance.capstone.Home.HomeInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomePolicyRepository extends JpaRepository<HomeInsurance, Long> {
}

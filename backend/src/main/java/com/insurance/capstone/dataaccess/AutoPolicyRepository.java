package com.insurance.capstone.dataaccess;

import com.insurance.capstone.Auto.AutoInsurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPolicyRepository extends JpaRepository<AutoInsurance, Long> {
}

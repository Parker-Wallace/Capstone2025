package com.insurance.capstone.dataaccess;

import com.insurance.capstone.User.Representative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepresentativeRepository extends JpaRepository<Representative, Long> {
}

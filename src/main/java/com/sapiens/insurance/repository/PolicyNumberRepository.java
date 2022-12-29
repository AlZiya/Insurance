package com.sapiens.insurance.repository;

import com.sapiens.insurance.entity.PolicyNumberSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyNumberRepository extends JpaRepository<PolicyNumberSequence, Long> {
}

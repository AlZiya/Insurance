package com.sapiens.insurance.repository;

import com.sapiens.insurance.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> getBySubmittedBy(String submittedBy);

    List<Proposal> getByStatus(int status);

    @Modifying
    @Transactional(rollbackOn = {Exception.class})
    @Query("update proposal p set p.status = :status where p.id= :id")
    int updateStatus(@Param("status") int status, @Param("id") long id);

    @Modifying
    @Transactional(rollbackOn = {Exception.class})
    @Query("delete proposal p where p.id= :id")
    int deleteProposal(@Param("id") long id);

    @Modifying
    @Transactional(rollbackOn = {Exception.class})
    @Query("update proposal p set p.policyNumber = :policyNumber where p.id= :id")
    int updatePolicyNumber(@Param("id") long id, @Param("policyNumber") String policyNumber);


}

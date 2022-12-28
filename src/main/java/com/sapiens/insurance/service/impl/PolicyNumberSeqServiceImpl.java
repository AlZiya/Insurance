package com.sapiens.insurance.service.impl;

import com.sapiens.insurance.entity.PolicyNumberSequence;
import com.sapiens.insurance.repository.PolicyNumberRepository;
import com.sapiens.insurance.service.PolicyNumberSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional(rollbackOn = {Exception.class})
public class PolicyNumberSeqServiceImpl implements PolicyNumberSeqService {

    @Autowired
    private PolicyNumberRepository policyNumberRepository;

    private static String policyNumber = "policy-";

    @Override
    @Transactional(rollbackOn = {Exception.class})
    synchronized public String getPolicyNumber() {
        Optional<PolicyNumberSequence> policyNumberSequence = policyNumberRepository.findById(1l);
        if (policyNumberSequence.isPresent()) {
            PolicyNumberSequence update = policyNumberSequence.get();
            update.setNumber(update.getNumber() + 1);
            policyNumberRepository.save(update);
            return policyNumber + update.getNumber();
        } else {
            PolicyNumberSequence update = PolicyNumberSequence.builder()
                    .number(100)
                    .build();
            policyNumberRepository.save(update);
            return policyNumber + update.getNumber();
        }
    }
}

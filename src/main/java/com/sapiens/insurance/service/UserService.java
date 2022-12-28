package com.sapiens.insurance.service;

import com.sapiens.insurance.entity.Proposal;
import com.sapiens.insurance.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity registerUser(User user);

    ResponseEntity submitProposal(Proposal proposal);
}

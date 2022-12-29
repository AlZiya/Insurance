package com.sapiens.insurance.service.impl;

import com.sapiens.insurance.entity.Proposal;
import com.sapiens.insurance.entity.User;
import com.sapiens.insurance.repository.ProposalRepository;
import com.sapiens.insurance.repository.UserRepository;
import com.sapiens.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public ResponseEntity registerUser(User user) {
        if (user.getRole() == null)
            user.setRole("USER");
        if (user.getDob().isAfter(LocalDate.now()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (user.getMobile() < 6000000000l || user.getMobile() > 9999999999l)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User response = userRepository.save(user);
        if (response == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity submitProposal(Proposal proposal) {
        if (proposal.getDob().isAfter(LocalDate.now()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (proposal.getMobile() < 6000000000l || proposal.getMobile() > 9999999999l)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        proposal.setStatus(1);
        Proposal response = proposalRepository.save(proposal);
        if (response == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    ;


}

package com.sapiens.insurance.controller;

import com.sapiens.insurance.configuration.UserAuthDetails;
import com.sapiens.insurance.entity.Proposal;
import com.sapiens.insurance.entity.User;
import com.sapiens.insurance.repository.ProposalRepository;
import com.sapiens.insurance.repository.UserRepository;
import com.sapiens.insurance.service.PolicyNumberSeqService;
import com.sapiens.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PolicyNumberSeqService policyNumberSeqService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registerUser")
    public String register(@ModelAttribute User user) {
        ResponseEntity response = userService.registerUser(user);
        if (response.getStatusCode().is2xxSuccessful())
            return "login";
        return "register";
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model) {
        model.addAttribute("proposal", new Proposal());
        return "dashboard";
    }

    @GetMapping("/adminDashboard")
    public String getAdminDashboard(Model model) {
        model.addAttribute("users", proposalRepository.getByStatus(1));
        return "admindashboard";
    }

    @GetMapping("/adminCancelled")
    public String adminCancelled(Model model) {
        model.addAttribute("users", proposalRepository.getByStatus(3));
        return "adminCancelled";
    }

    @GetMapping("/adminApproved")
    public String adminApproved(Model model) {
        model.addAttribute("users", proposalRepository.getByStatus(2));
        return "adminApproved";
    }


    @PostMapping("/submitProposal")
    public String submitProposal(@ModelAttribute Proposal proposal, Authentication authentication) {
        System.out.println(proposal);
        UserAuthDetails details = (UserAuthDetails) authentication.getPrincipal();
        proposal.setSubmittedBy(details.getUsername());
        ResponseEntity response = userService.submitProposal(proposal);
        if (response.getStatusCode().is2xxSuccessful())
            return "redirect:/dashboard";
        return "dashboard";
    }

    @PostMapping("/submitProposalv1")
    public String submitProposal(@ModelAttribute Proposal proposal, Authentication authentication, @RequestParam long id) {
        System.out.println(proposal);
        Proposal response1 = proposalRepository.getById(id);
        UserAuthDetails details = (UserAuthDetails) authentication.getPrincipal();
        response1.setSubmittedBy(details.getUsername());
        ResponseEntity response = userService.submitProposal(response1);
        if (response.getStatusCode().is2xxSuccessful())
            return "redirect:/dashboard";
        return "dashboard";
    }

    @GetMapping("/proposalStatus")
    public String proposalStatus(@RequestParam int status, @RequestParam long id) {
        int response = proposalRepository.updateStatus(status, id);
        if (status == 2) {
            proposalRepository.updatePolicyNumber(id, policyNumberSeqService.getPolicyNumber());
            return "redirect:/adminApproved";
        }
        return "redirect:/adminCancelled";
    }

    @GetMapping("/deleteProposal")
    public String deleteProposal(@RequestParam long id) {
        int response = proposalRepository.deleteProposal(id);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/proposalUpdatePage/{id}")
    public String proposalUpdatePage(@PathVariable("id") long id, Model model) {
        Optional<Proposal> proposal = proposalRepository.findById(id);
        model.addAttribute("proposal", proposal.get());
        return "updateProposal";
    }

    @PostMapping("/proposalUpdate")
    public String proposalUpdate(@ModelAttribute Proposal proposal) {
        Proposal temp = proposalRepository.getById(proposal.getId());
        proposal.setStatus(temp.getStatus());
        proposal.setSubmittedBy(temp.getSubmittedBy());
        Proposal response = proposalRepository.save(proposal);
        return "redirect:/adminDashboard";
    }

    @GetMapping("/appliedProposals")
    public String appliedProposals(Model model, Authentication authentication) {
        UserAuthDetails details = (UserAuthDetails) authentication.getPrincipal();
        System.out.println(details.getUsername());
        List<Proposal> proposals = proposalRepository.getBySubmittedBy(details.getUsername());
        model.addAttribute("proposals", proposals);
        return "appliedProposal";
    }

    @PostMapping("/payment")
    public String getPaymentPage(@ModelAttribute Proposal proposal, Model model) {
        Proposal response = proposalRepository.save(proposal);
        model.addAttribute("proposal", response);
        return "payment";
    }

}

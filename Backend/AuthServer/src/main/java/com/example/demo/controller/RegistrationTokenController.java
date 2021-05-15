package com.example.demo.controller;

import com.example.demo.domain.RegistrationTokenDomain;
import com.example.demo.domain.UserDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.RegistrationTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RegistrationTokenService;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class RegistrationTokenController {
    @Autowired
    private RegistrationTokenService registrationTokenService;

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/generateToken")
    public RegistrationTokenResponse generateToken(@RequestBody RegistrationTokenDomain registrationTokenDomain) {
        RegistrationTokenResponse response = new RegistrationTokenResponse();
        String token = UUID.randomUUID().toString();
        String recipient = registrationTokenDomain.getEmail();
        String createdBy = registrationTokenDomain.getCreatedBy();
        if (recipient == null) {
            response.setServiceStatus(new ServiceStatus("FAIL", false, "Null Email"));
            return response;
        }

        registrationTokenService.createRegistrationToken(token, recipient, createdBy);
        String subject = "Registration Link";
        String registrationUrl = "http://localhost:4200/account/register?token=" + token;
        String message = "Please use the link below to register new account:";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        System.out.println("Email: " + recipient);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + "\r\n" + registrationUrl);
        emailSender.send(simpleMailMessage);
        response.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        return response;
    }

    @GetMapping("/register/{token}")
    public RegistrationTokenResponse checkRegistrationTokenValidity(@PathVariable String token) {
        RegistrationTokenResponse response = new RegistrationTokenResponse();
        RegistrationTokenDomain registrationTokenDomain = new RegistrationTokenDomain();
        String email = registrationTokenService.getEmailFromToken(token);
        if (email.equals("error")) {
            response.setServiceStatus(new ServiceStatus("FAIL", false, "Invalid Registration Token"));
            return response;
        }
        registrationTokenDomain.setEmail(email);
        response.setRegistrationTokenDomain(registrationTokenDomain);
        response.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        return response;
    }

    @PostMapping("/register")
    public RegistrationTokenResponse doRegister(@RequestBody UserDomain userDomain) {
        RegistrationTokenResponse response = new RegistrationTokenResponse();
        String username = userDomain.getUsername();
        String pwd = userDomain.getPassword();
        String email = userDomain.getEmail();
        int userId = 0;
        if (username == null || pwd == null || (userId = registrationTokenService.createUser(username, pwd, email)) == 0 ) {
            response.setServiceStatus(new ServiceStatus("FAIL", false, "Duplicate username"));
            return response;
        }
        response.setUserId(userId);
        response.setServiceStatus(new ServiceStatus("SUCCESS", true, ""));
        return response;
    }
}

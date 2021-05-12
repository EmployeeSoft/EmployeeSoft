package com.example.demo.controller;

import com.example.demo.domain.RegistrationTokenDomain;
import com.example.demo.domain.common.ServiceStatus;
import com.example.demo.domain.response.RegistrationTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.RegistrationTokenService;

import java.util.UUID;

@RestController
public class RegistrationTokenController {
    @Autowired
    private RegistrationTokenService registrationTokenService;

    @Autowired
    private JavaMailSender emailSender;
    @ResponseBody
    @PostMapping("/generateToken")
    public RegistrationTokenResponse generateToken(@RequestBody RegistrationTokenDomain registrationTokenDomain) {
        RegistrationTokenResponse response = new RegistrationTokenResponse();
        String token = UUID.randomUUID().toString();
        String recipient = registrationTokenDomain.getEmail();
        String createdBy = registrationTokenDomain.getCreatedBy();
        if (!registrationTokenService.createRegistrationToken(token, recipient, createdBy)) {
            response.setServiceStatus(new ServiceStatus("FAIL", false, "This email has" +
                    " already existed in the Database"));
            return response;
        }

        String subject = "Registration Link";
        String registrationUrl = "http://localhost:8080/register?token=" + token;
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
}

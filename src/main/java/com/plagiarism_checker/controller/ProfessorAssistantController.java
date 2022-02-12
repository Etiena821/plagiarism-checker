package com.plagiarism_checker.controller;

import com.plagiarism_checker.config.JwtTokenProvider;
import com.plagiarism_checker.config.UserDetailsImplService;
import com.plagiarism_checker.message.ResponseMessage;
import com.plagiarism_checker.model.LoginDTO;
import com.plagiarism_checker.model.ProfessorAssistantDTO;
import com.plagiarism_checker.service.BlackListService;
import com.plagiarism_checker.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class ProfessorAssistantController {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsImplService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final UserServiceImpl userService;

    private final BlackListService blackListService;

    @PostMapping("/addProfessor")
    public String addUser(@RequestBody ProfessorAssistantDTO professorAssistantDTO) {

        if (userService.getUserByUsername(professorAssistantDTO.getUsername()) != null) {

            return "User already exist";
        }

        userService.addProfessor(professorAssistantDTO);

        return "Successful";
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody LoginDTO loginDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginDTO.getUsername());

        final String jwt = jwtTokenProvider.generateToken(userDetails);

        return new ResponseEntity<>(new ResponseMessage(jwt), HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(HttpServletRequest httpServletRequest) {

        final String token = httpServletRequest.getHeader("Authorization");

        blackListService.blackListToken(token);

        return new ResponseEntity<>(new ResponseMessage("Logout Successful"), HttpStatus.OK);

    }

}


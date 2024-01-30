package com.cursos.springsecuritycourse.controller;

import com.cursos.springsecuritycourse.dto.AuthenticationRequest;
import com.cursos.springsecuritycourse.dto.AuthenticationResponse;
import com.cursos.springsecuritycourse.entity.Product;
import com.cursos.springsecuritycourse.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse jwtDto = authenticationService.login(authenticationRequest);

        return ResponseEntity.ok(jwtDto);
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public String publicAccessEndPoint(){
        return "This endpoint is public";
    }

}

package com.example.wallet.controller.rest;

import com.example.wallet.services.token.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

///  Based on <a href= https://github.com/danvega/jwt/tree/master>JWT repo by Dan Vega</a>
@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(description = "Generate a token, to be used for app authentication")
    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

}
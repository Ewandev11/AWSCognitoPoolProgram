package com.example.demo.Controller;

import com.example.demo.DTO.AuthResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.Services.CognitoLoginPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private CognitoLoginPoolService cognitoLoginPoolService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            String token = cognitoLoginPoolService.authenticateUser(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            //  Log the actual error for debugging
            e.printStackTrace(); // This will show in your Spring Boot console
            return ResponseEntity.status(401).build();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<String> SignUp(@RequestBody SignupRequest request){
        try{
            cognitoLoginPoolService.createUser(request.getEmail(), request.getPassword(), request.getName());
            return ResponseEntity.ok("User Created Successfully!");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Sign up failed: " +e.getMessage());
        }
    }

}

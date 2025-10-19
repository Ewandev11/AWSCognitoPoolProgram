package com.example.demo.DTO;

public class AuthResponse {
    private String token;

    public AuthResponse(String token){
        this.token = token;
    }
    public String getToken(){
        return token;
    }
    public String setToken() {
        this.token = token;
        return "";
    }
}

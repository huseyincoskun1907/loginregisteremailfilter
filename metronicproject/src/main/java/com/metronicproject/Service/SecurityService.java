package com.metronicproject.Service;

public interface SecurityService {

    String findLoggedInEmail();

    void autologin(String email, String password);
}
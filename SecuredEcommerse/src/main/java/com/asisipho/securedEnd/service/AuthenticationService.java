package com.asisipho.securedEnd.service;

import com.asisipho.securedEnd.model.request.AuthenticationRequest;
import com.asisipho.securedEnd.model.request.RegisterRequest;
import com.asisipho.securedEnd.model.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface AuthenticationService {

  public AuthenticationResponse register(RegisterRequest request);

  public AuthenticationResponse authenticate(AuthenticationRequest request);

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

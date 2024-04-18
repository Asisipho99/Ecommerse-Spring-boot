package com.asisipho.securedEnd.service;

import com.asisipho.securedEnd.model.request.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {

    public void changePassword(ChangePasswordRequest request, Principal connectedUser);
}

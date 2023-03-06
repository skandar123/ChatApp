package com.viralfission.chat.service;

import com.viralfission.chat.model.UserDtls;

import java.util.List;

public interface UserService {
    public UserDtls createUser(UserDtls user);
    public boolean checkEmail(String email);
    public List<UserDtls> getAllContacts();
    public UserDtls getMemberById(int id);
}

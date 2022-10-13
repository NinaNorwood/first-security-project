package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.AppUserDTO;
import com.example.backend.repo.AppUserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    PasswordEncoder passwordEncoder;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(AppUserDTO appUserDTO) {
        String hashPassword = passwordEncoder.encode(appUserDTO.getPasswordHash());

        AppUser appUser = new AppUser();
        appUser.setUserName(appUserDTO.getUserName());
        appUser.setPasswordHash(hashPassword);
        appUser.setRoles(List.of("USER"));

        return appUserRepo.save(appUser).getUserName();
    }
}

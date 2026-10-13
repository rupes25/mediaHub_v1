package com.mediaHub.backend.service;

import com.mediaHub.backend.dto.RegisterReq;
import com.mediaHub.backend.dto.RegisterRes;
import com.mediaHub.backend.model.User;
import com.mediaHub.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public RegisterRes register(RegisterReq req, HttpServletResponse response) {
//        to check whether the coming value has some actual value or not.
        String fullName = normalize(req.getFullName());
        String email = normalize(req.getEmail());
        String username = normalize(req.getUsername());
        String password = normalize(req.getPassword());

//        if the incoming value is null then return with a valid response.
        if(fullName == null || email == null || username == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"All fields are important.");
        }

//        checking whether if any other user exists with the provided email or username.
        email= email.toLowerCase();

        if(userRepository.existsByUsername(username) || userRepository.existsByEmail(email)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User already exists with this email or username.");

        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        user.setDateOfBirth(req.getDateOfBirth());
        user.setPassword(req.getPassword());

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser,"User registered successfully.");


    }





















        private String normalize(String value){
            if(value == null){
                return null;
            }
            String trimmedValue = value.trim();

            if(trimmedValue.isEmpty()){
                return null;
            }
            return trimmedValue;

        }

        private RegisterRes mapToDto(User user,String message){
            RegisterRes res = new RegisterRes();
            res.setFullName(user.getFullName());
            res.setEmail(user.getEmail());
            res.setUsername(user.getUsername());
            res.setDateOfBirth(user.getDateOfBirth());
            res.setMessage(message);

            return res;
        }


}

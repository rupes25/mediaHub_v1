package com.mediaHub.backend.service;

import com.mediaHub.backend.dto.LoginReq;
import com.mediaHub.backend.dto.LoginRes;
import com.mediaHub.backend.dto.RegisterReq;
import com.mediaHub.backend.dto.RegisterRes;
import com.mediaHub.backend.model.User;
import com.mediaHub.backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final boolean secureCookie;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            @Value("${app.cookie.secure:false}") boolean secureCookie
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.secureCookie = secureCookie;
    }

    public RegisterRes register(RegisterReq req, HttpServletResponse response) {
//        to check whether the coming value has some actual value or not.
        String fullName = normalize(req.getFullName());
        String email = normalize(req.getEmail());
        String username = normalize(req.getUsername());
        String password = normalize(req.getPassword());

//        if the incoming value is null then return with a valid response.
        if(fullName == null || email == null || username == null || req.getPassword() == null){
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
        user.setPassword(
                passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        setTokenCookie(savedUser, response);
        return mapToDto(savedUser,"User registered successfully.");


    }




    public LoginRes login(LoginReq req,HttpServletResponse response){
        String emailOrUsername = normalize(req.getEmail());

        if(emailOrUsername == null || req.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid login credentials.");
        }

        emailOrUsername = emailOrUsername.toLowerCase();

       Optional<User> user = userRepository.findByEmail(emailOrUsername);
       if (user.isEmpty()){
           user = userRepository.findByUsername(emailOrUsername);
       }

       if (!req.getPassword().equals(user.get().getPassword())){
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid login credentials.");
       }

       LoginRes res = new LoginRes();
       res.setMessage("Login successfully.");
       return res;

    }




    public void logout(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie
                .from("token","")
                .httpOnly(true)
                .secure(secureCookie)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                cookie.toString()
        );

    }

    private void setTokenCookie(User user, HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie
                .from("token", jwtService.generateToken(user))
                .httpOnly(true)
                .secure(secureCookie)
                .path("/")
                .maxAge(JwtService.TOKEN_TTL)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

//    public RegisterRes getProfile(Authentication authentication) {
//        User user = userRepository.findById(authentication.getName())
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.UNAUTHORIZED, "User account was not found."
//                ));
//        return mapToDto(user, "Profile loaded.");
//    }
















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

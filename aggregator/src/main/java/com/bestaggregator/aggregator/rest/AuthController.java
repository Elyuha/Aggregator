package com.bestaggregator.aggregator.rest;

import com.bestaggregator.aggregator.entity.User;
import com.bestaggregator.aggregator.request.AuthRequest;
import com.bestaggregator.aggregator.request.RegistrationRequest;
import com.bestaggregator.aggregator.response.AuthResponse;
import com.bestaggregator.aggregator.security.jwt.JwtProvider;
import com.bestaggregator.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        if (userService.findByPhone(registrationRequest.getPhone()) != null)
            return new ResponseEntity<>("This phone is already used!", HttpStatus.CONFLICT);

        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setPhone(registrationRequest.getPhone());
        userService.save(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/auth",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest request) {
        User user = userService.findByPhoneAndPassword(request.getPhone(), request.getPassword());
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String token = jwtProvider.generateToken(user.getPhone());
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}

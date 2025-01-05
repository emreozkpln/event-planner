package dev.buddly.event_planner.service;

import dev.buddly.event_planner.dto.request.AuthenticationRequest;
import dev.buddly.event_planner.dto.request.RegistrationRequest;
import dev.buddly.event_planner.dto.response.AuthenticationResponse;
import dev.buddly.event_planner.exception.OperationNotPermittedException;
import dev.buddly.event_planner.model.User;
import dev.buddly.event_planner.repo.UserRepository;
import dev.buddly.event_planner.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public void register(RegistrationRequest request) {
        if(userRepository.findByEmail(request.email()).isPresent()) {
            throw new OperationNotPermittedException("User already exists");
        }
        var user = new User();
                user.setFirstname(request.firstname());
                user.setLastname(request.lastname());
                user.setEmail(request.email());
                user.setPassword(passwordEncoder.encode(request.password()));
                user.setAccountLocked(false);
                user.setEnabled(true);

        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (Exception e) {
            return new AuthenticationResponse(null, "Invalid username or password");
        }
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();

        if(!user.isEnabled()){
            throw new OperationNotPermittedException("User not enabled");
        }

        var claims = new HashMap<String, Object>();
        claims.put("fullname",user.fullName());
        var token = jwtService.generateToken(claims,user);
        return new AuthenticationResponse(token,"User successfully login");
    }
}
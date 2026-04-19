package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.config.TokenConfig;
import dev.pedro.foodflow_api.dto.user.LoginRequestDTO;
import dev.pedro.foodflow_api.dto.user.LoginResponseDTO;
import dev.pedro.foodflow_api.dto.user.RegisterRequestDTO;
import dev.pedro.foodflow_api.dto.user.RegisterResponseDTO;
import dev.pedro.foodflow_api.entities.User;
import dev.pedro.foodflow_api.mappers.RegisterUserMapper;
import dev.pedro.foodflow_api.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RegisterUserMapper registerUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public UserService(UserRepository userRepository, RegisterUserMapper registerUserMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.registerUserMapper = registerUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public RegisterResponseDTO register(RegisterRequestDTO request) {
        var user = new User(request.email(), passwordEncoder.encode(request.password()));
        return registerUserMapper.toDTO(userRepository.save(user));
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        var userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return new LoginResponseDTO(token);
    }
}

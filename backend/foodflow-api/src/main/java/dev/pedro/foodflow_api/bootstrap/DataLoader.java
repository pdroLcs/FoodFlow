package dev.pedro.foodflow_api.bootstrap;

import dev.pedro.foodflow_api.entities.User;
import dev.pedro.foodflow_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static dev.pedro.foodflow_api.entities.Role.ADMIN;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean existsAdmin = userRepository.existsByRole(ADMIN);

        if (!existsAdmin) {
            var admin = new User(adminEmail, passwordEncoder.encode(adminPassword));
            admin.setRole(ADMIN);
            userRepository.save(admin);
        }
    }
}

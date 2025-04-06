package com.izorai.pfa;

import com.izorai.pfa.module1.security.UserAuth;
import com.izorai.pfa.module1.security.UserAuthRep;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserAuthRep userAuthRep;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (userAuthRep.findByUsername("admin") == null) {
            UserAuth admin = new UserAuth();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Encodage sécurisé
            admin.setRoles(Set.of("ADMIN"));
            userAuthRep.save(admin);
            System.out.println("✅ Création de l'utilisateur admin avec mot de passe encodé");
        }

        // Create regular user if not exists
        if (userAuthRep.findByUsername("user") == null) {
            UserAuth user = new UserAuth();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123")); // Encodage sécurisé
            user.setRoles(Set.of("USER"));
            userAuthRep.save(user);
            System.out.println("✅ Création de l'utilisateur standard avec mot de passe encodé");
        }

        // Vérification des utilisateurs existants
        System.out.println("👥 Utilisateurs dans la base:");
        userAuthRep.findAll().forEach(u ->
                System.out.println(" - " + u.getUsername() + " (" + u.getRoles() + ")")
        );
    }
}
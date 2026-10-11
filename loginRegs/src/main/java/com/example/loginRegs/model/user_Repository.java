
package com.example.loginRegs.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface user_Repository
        extends JpaRepository<user_entity, Long> {

    Optional<user_entity> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

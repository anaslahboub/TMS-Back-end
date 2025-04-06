package com.izorai.pfa.module1.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserAuthRep extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUsername(String username);
}

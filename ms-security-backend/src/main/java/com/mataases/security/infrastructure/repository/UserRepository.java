package com.mataases.security.infrastructure.repository;

import com.mataases.security.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
}
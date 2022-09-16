package com.mataases.security.infrastructure.repository;
import com.mataases.security.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    public List<Authority> findByUsername(String username);
}
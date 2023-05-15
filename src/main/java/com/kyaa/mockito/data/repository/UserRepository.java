package com.kyaa.mockito.data.repository;

import com.kyaa.mockito.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

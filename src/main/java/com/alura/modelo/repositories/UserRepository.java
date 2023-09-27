package com.alura.modelo.repositories;

import com.alura.modelo.Topic;
import com.alura.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

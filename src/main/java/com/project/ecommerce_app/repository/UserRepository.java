package com.project.ecommerce_app.repository;
import com.project.ecommerce_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>{

    User findByUsername(String username);
    User findByEmail(String email);

    boolean existsByUsername(String username);
//    Optional<User> findById(Integer id);
}

package com.example.wallet.persistence.user;

import com.example.wallet.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(nativeQuery = true, value = "SELECT user_id,username,password FROM users where username = ?")
    User findByUsername(String username);

}

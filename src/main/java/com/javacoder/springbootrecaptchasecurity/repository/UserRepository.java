package com.javacoder.springbootrecaptchasecurity.repository;

import com.javacoder.springbootrecaptchasecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}

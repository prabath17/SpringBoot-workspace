package com.example.SPRING_DEMO_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SPRING_DEMO_1.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity,Long>{

}

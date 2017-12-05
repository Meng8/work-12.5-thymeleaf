package com.example.entity;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Serializable>{


}

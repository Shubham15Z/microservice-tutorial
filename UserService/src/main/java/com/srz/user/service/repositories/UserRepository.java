package com.srz.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srz.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String> 
{

}

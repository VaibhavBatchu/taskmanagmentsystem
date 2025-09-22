package com.klef.fsad.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
  public User findByEmailAndPassword(String email, String password);
  public User findById(Long id);
  public User findByEmail(String email);
  public User findByContact(String contact);
}


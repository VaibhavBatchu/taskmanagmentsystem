package com.klef.fsad.service;

import java.util.List;

import com.klef.fsad.model.Admin;
import com.klef.fsad.model.Task;
import com.klef.fsad.model.User;

public interface AdminService 
{
  public Admin verifyAdminLogin(Admin admin);
  public User addUser(User user);
  public List<User> viewAllUsers();
  public String deleteUser(int id);
  public boolean isEmailExist(String email);
  public boolean isContactExist(String contact);
  
  public List<Task> getAllTasks();
}

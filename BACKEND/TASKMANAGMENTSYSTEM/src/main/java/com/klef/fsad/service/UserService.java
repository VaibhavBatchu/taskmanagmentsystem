package com.klef.fsad.service;

import java.util.List;
import java.util.Optional;

import com.klef.fsad.model.SelfTask;
import com.klef.fsad.model.Task;
import com.klef.fsad.model.TaskProgress;
import com.klef.fsad.model.User;

public interface UserService 
{
  public User verifyUserLogin(User user);
  public List<User> getAllUsers();
  public User getUserById(Long id);
  public String updateUser(User user);
  public String updateUserImage(int id, byte[] imageBytes);
  
  public String addTask(Task task);
  public List<Task> getTasksAssignedBy(int id);
  public List<Task> getTasksAssignedTo(int id);
  public String deleteTask(Long id);
  public void updateTask(Long id,double progress,String status);
  public String updateTaskProgress(TaskProgress taskProgress);
  
  public List<TaskProgress> myreviewtaskprogress(Long id);
  public Optional<TaskProgress> getTaskProgessById(Long id);
  
  public void updateReviewStatus(Long id,String remarks);
  
  
  public String addSelfTask(SelfTask selfTask);
  public List<SelfTask> getAllSelfTasks();
  public String updateSelfTask(Long id,String status);
  
  public String resetPassword(String email,String password);
  
}



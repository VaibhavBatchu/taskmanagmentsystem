package com.klef.fsad.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.Task;



@Repository
public interface TaskRepository extends JpaRepository<Task,Long>
{
  public List<Task> findByAssignedBy(int id);
  public List<Task> findByAssignedTo(int id);
}



package com.klef.fsad.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.fsad.model.SelfTask;

@Repository
public interface SelfTaskRepository extends JpaRepository<SelfTask,Long>
{

}

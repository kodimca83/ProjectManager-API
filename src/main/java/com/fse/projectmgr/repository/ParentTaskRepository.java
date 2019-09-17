package com.fse.projectmgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.projectmgr.model.ParentTask;

public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer> {

}

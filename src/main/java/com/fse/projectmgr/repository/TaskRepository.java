package com.fse.projectmgr.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fse.projectmgr.model.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {

	@Query(value="SELECT parent_id FROM parent_task WHERE lower(parent_task) = lower(?1)", nativeQuery=true)
	Integer findParentTask(String taskName);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE task SET delete_flag='Y' where task_id = :taskId", nativeQuery=true)
	void endTask(@Param("taskId") Integer taskId);
}

package sn.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.security.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}

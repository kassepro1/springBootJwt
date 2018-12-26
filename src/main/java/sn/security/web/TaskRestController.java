package sn.security.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sn.security.dao.TaskRepository;
import sn.security.entities.Task;

@RestController
public class TaskRestController {
	@Autowired
	private TaskRepository taskRepository;
	
@GetMapping("tasks")
public List<Task> tasks(){
	return taskRepository.findAll();
}
@PostMapping("/addTasks")
public Task addTasks(@RequestBody Task t) {
	System.out.println("called");
	return taskRepository.save(t);
}

}

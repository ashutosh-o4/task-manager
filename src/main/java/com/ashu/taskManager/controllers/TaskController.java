package com.ashu.taskManager.controllers;

import com.ashu.taskManager.DTOs.Taskdto;
import com.ashu.taskManager.entities.TaskEntity;
import com.ashu.taskManager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getTask(){
        var tasks=taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id){
        var task=taskService.getTaskById(id);
        if(task==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody Taskdto taskdto){
        var task=taskService.addtask(taskdto.getTitle(),taskdto.getDescription(),taskdto.getDeadline());
        return  ResponseEntity.ok(task);
    }
}

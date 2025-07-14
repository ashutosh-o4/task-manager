package com.ashu.taskManager.controllers;

import com.ashu.taskManager.DTOs.ErrorDTO;
import com.ashu.taskManager.DTOs.Taskdto;
import com.ashu.taskManager.DTOs.UpdateTaskDTO;
import com.ashu.taskManager.entities.TaskEntity;
import com.ashu.taskManager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    public ResponseEntity<TaskEntity> addTask(@RequestBody Taskdto taskdto)throws ParseException{
        var task=taskService.addtask(taskdto.getTitle(),taskdto.getDescription(),taskdto.getDeadline());
        return  ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") int id, @RequestBody UpdateTaskDTO updatetask)throws ParseException{
        var task=taskService.updateTask(id,updatetask.getDescription(),updatetask.getDeadline(),updatetask.isIscomplete());

        if(task==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorDTO("Invalid date format."));
        }

        return ResponseEntity.internalServerError().body(new ErrorDTO("Internal server error"));
    }
}

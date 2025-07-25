package com.ashu.taskManager.controllers;

import com.ashu.taskManager.DTOs.ErrorDTO;
import com.ashu.taskManager.DTOs.TaskResponseDTO;
import com.ashu.taskManager.DTOs.Taskdto;
import com.ashu.taskManager.DTOs.UpdateTaskDTO;
import com.ashu.taskManager.entities.TaskEntity;
import com.ashu.taskManager.service.NotesService;
import com.ashu.taskManager.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final NotesService notesService;
    private ModelMapper modelMapper=new ModelMapper();

    public TaskController(TaskService taskService,NotesService notesService) {
        this.notesService=notesService;
        this.taskService = taskService;
    }


    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getTask(){
        var tasks=taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task=taskService.getTaskById(id);
        var notes=notesService.getNotesForTask(id);
        if(task==null)
            return ResponseEntity.notFound().build();
        var taskResponse=modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody Taskdto taskdto)throws ParseException{
        var task=taskService.addtask(taskdto.getTitle(),taskdto.getDescription(),taskdto.getDeadline());
        return  ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") int id, @RequestBody UpdateTaskDTO updateTask)throws ParseException{
        var task=taskService.updateTask(id,updateTask.getDescription(),updateTask.getDeadline(),updateTask.isIscomplete());

        if(task==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskEntity> deleteTask(@PathVariable("id") int id){
        if(taskService.deleteTask(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleErrors(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorDTO("Invalid date format."));
        }

        return ResponseEntity.internalServerError().body(new ErrorDTO("Internal server error"));
    }
}

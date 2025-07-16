package com.ashu.taskManager.controllers;

import com.ashu.taskManager.DTOs.NotesDTO;
import com.ashu.taskManager.DTOs.NotesResponseDTO;
import com.ashu.taskManager.entities.NotesEntity;
import com.ashu.taskManager.service.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task/{taskId}/notes")
public class NotesController {

    private NotesService notesService ;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes=notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<NotesResponseDTO> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody NotesDTO notesdto){
        var note=notesService.addNotesForTask(taskId,notesdto.getTitle(),notesdto.getBody());
        return ResponseEntity.ok(new NotesResponseDTO(taskId,note));
    }
}

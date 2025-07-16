package com.ashu.taskManager.DTOs;

import com.ashu.taskManager.entities.NotesEntity;

public class NotesResponseDTO {
    private int taskId;
    private NotesEntity note;

    public NotesResponseDTO(int taskId, NotesEntity note) {
        this.taskId = taskId;
        this.note = note;
    }

    public NotesResponseDTO() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public NotesEntity getNote() {
        return note;
    }

    public void setNote(NotesEntity note) {
        this.note = note;
    }
}

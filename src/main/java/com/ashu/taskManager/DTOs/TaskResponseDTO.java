package com.ashu.taskManager.DTOs;

import com.ashu.taskManager.entities.NotesEntity;

import java.util.Date;
import java.util.List;

public class TaskResponseDTO {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean iscomplete;
    private List<NotesEntity> notes;
    public TaskResponseDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(boolean iscomplete) {
        this.iscomplete = iscomplete;
    }

    public List<NotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesEntity> notes) {
        this.notes = notes;
    }
}

package com.ashu.taskManager.DTOs;

public class UpdateTaskDTO {
    private String description;
    private String deadline;
    private boolean iscomplete;

    UpdateTaskDTO(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(boolean iscommplete) {
        this.iscomplete = iscommplete;
    }
}

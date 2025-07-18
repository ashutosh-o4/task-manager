package com.ashu.taskManager.service;

import com.ashu.taskManager.entities.NotesEntity;
import com.ashu.taskManager.entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class NotesService {
    private final TaskService taskService;
    private HashMap<Integer,TaskNotesHolder> taskNoteHolders=new HashMap<>();

    public NotesService(TaskService taskService){
        this.taskService=taskService;
    }
    class TaskNotesHolder{
        protected int noteId=1;
        protected ArrayList<NotesEntity> notes=new ArrayList<>();
    }

    public List<NotesEntity> getNotesForTask(int taskid){
        TaskEntity task=taskService.getTaskById(taskid);
        if(task == null){
            return null;
        }
        if(taskNoteHolders.get(taskid)==null){
            taskNoteHolders.put(taskid,new TaskNotesHolder());
        }
        return taskNoteHolders.get(taskid).notes;
    }

    public NotesEntity addNotesForTask(int taskId,String title,String body){
        TaskEntity task=taskService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNoteHolders.get(taskId)==null){
            taskNoteHolders.put(taskId,new TaskNotesHolder());
        }
        TaskNotesHolder taskNotesHolder=taskNoteHolders.get(taskId);
        NotesEntity note=new NotesEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }

    public boolean deleteAllNoteForTask(int taskid){
        TaskEntity task=taskService.getTaskById(taskid);
        if(task==null)
            return false;
        taskNoteHolders.remove(taskid);
        return true;
    }

    public boolean deleteNoteForTask(int taskId, int noteId) {
        TaskNotesHolder holder = taskNoteHolders.get(taskId);
        if (holder == null) {
            return false;
        }
        return holder.notes.removeIf(note -> note.getId() == noteId);
    }
}

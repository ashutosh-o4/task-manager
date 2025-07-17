package com.ashu.taskManager.service;

import com.ashu.taskManager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
 

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks=new ArrayList<>();
    private int taskid=1;
    private final SimpleDateFormat deadlineFormater =new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addtask(String title,String description,String deadline) throws ParseException {
        TaskEntity task=new TaskEntity();
        task.setId(taskid);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormater.parse(deadline));
        task.setIscomplete(false);
        tasks.add(task);
        taskid++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id){
        for(TaskEntity t:tasks){
            if(t.getId()==id)
                return t;
        }
        return null;
    }

    public TaskEntity updateTask(int id,String description,String deadline,boolean iscomplete)throws ParseException{
        TaskEntity task=getTaskById(id);
        if(task==null)
            return null;
        if(description!=null)
            task.setDescription(description);
        if(deadline!=null)
            task.setDeadline(deadlineFormater.parse(deadline));
        task.setIscomplete(iscomplete);
        return task;

    }
    public boolean deleteTask(int id){
        TaskEntity task=getTaskById(id);
        if(task==null)
        return false;
        tasks.remove(task);
        return true;
    }
}

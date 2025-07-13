package com.ashu.taskManager.service;

import com.ashu.taskManager.entities.TaskEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks=new ArrayList<>();
    private int taskid=1;

    public TaskEntity addtask(String title,String description,String deadline){
        TaskEntity task=new TaskEntity();
        task.setId(taskid);
        task.setTitle(title);
        task.setDescription(description);
//        task.setDeadline(new Date(deadline));
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

}

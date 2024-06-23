package com.gestortareas.task.application.port;

import java.util.List;

import com.gestortareas.task.domain.Task;

public interface TaskService {
    void addTask(String description);
    List<Task> getAllTasks();
}

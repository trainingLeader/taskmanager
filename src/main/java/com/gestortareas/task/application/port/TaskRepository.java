package com.gestortareas.task.application.port;

import java.util.List;

import com.gestortareas.task.domain.Task;

public interface TaskRepository {
    void addTask(Task task);
    List<Task> getAllTasks();
}

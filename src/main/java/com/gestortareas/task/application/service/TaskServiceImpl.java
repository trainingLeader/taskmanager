package com.gestortareas.task.application.service;

import java.util.List;

import com.gestortareas.task.application.port.TaskRepository;
import com.gestortareas.task.application.port.TaskService;
import com.gestortareas.task.domain.Task;

public class TaskServiceImpl implements TaskService {
        
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }
    @Override
    public void addTask(String description) {
        Task task = new Task(0, description); // La ID será asignada por el repositorio
        repository.addTask(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.getAllTasks();
    }

}

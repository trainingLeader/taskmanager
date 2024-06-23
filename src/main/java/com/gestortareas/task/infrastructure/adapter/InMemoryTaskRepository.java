package com.gestortareas.task.infrastructure.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.gestortareas.task.application.port.TaskRepository;
import com.gestortareas.task.domain.Task;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public void addTask(Task task) {
        task.setId(idGenerator.incrementAndGet());
        tasks.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

}

package com.gestortareas;

import java.util.List;
import java.util.Scanner;

import com.gestortareas.task.application.port.TaskService;
import com.gestortareas.task.application.service.TaskServiceImpl;
import com.gestortareas.task.domain.Task;
//import com.gestortareas.task.infrastructure.adapter.InMemoryTaskRepository;
import com.gestortareas.task.infrastructure.adapter.MySQLTaskRepository;

public class Main {

    private static TaskService service;
    public static void main(String[] args) {
        //service = new TaskServiceImpl(new InMemoryTaskRepository());
        service = new TaskServiceImpl(new MySQLTaskRepository());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    private static void addTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        service.addTask(description);
        System.out.println("Task added.");
    }

    private static void listTasks() {
        List<Task> tasks = service.getAllTasks();
        for (Task task : tasks) {
            System.out.println(task.getDescription());
        }
    }
}
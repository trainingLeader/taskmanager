package com.gestortareas.task.infrastructure.adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gestortareas.config.DatabaseConfig;
import com.gestortareas.task.application.port.TaskRepository;
import com.gestortareas.task.domain.Task;

public class MySQLTaskRepository implements TaskRepository{

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (description) VALUES (?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, task.getDescription());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error adding task", e);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("description"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching tasks", e);
        }
        return tasks;
    }

}

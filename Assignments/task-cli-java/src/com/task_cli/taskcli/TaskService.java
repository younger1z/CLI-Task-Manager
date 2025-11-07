package com.task_cli.taskcli;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task addTask(String title) {
        return repository.create(title);
    }

    public List<Task> listTasks() {
        return repository.findAll();
    }

    public Optional<Task> updateStatus(long id, TaskStatus status) {
        Optional<Task> opt = repository.findById(id);
        opt.ifPresent(task -> task.setStatus(status));
        return opt;
    }

    public boolean deleteTask(long id) {
        return repository.deleteById(id);
    }
}



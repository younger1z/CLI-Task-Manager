package com.task_cli.taskcli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private final List<Task> tasks;
    private long nextId;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
        this.nextId = 1L;
    }

    public synchronized Task create(String title) {
        Task task = new Task(nextId++, title);
        tasks.add(task);
        return task;
    }

    public synchronized List<Task> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(tasks));
    }

    public synchronized Optional<Task> findById(long id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public synchronized boolean deleteById(long id) {
        return tasks.removeIf(t -> t.getId() == id);
    }
}



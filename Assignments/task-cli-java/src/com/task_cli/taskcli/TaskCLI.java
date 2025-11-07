package com.task_cli.taskcli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class TaskCLI {
    private final TaskService service;
    private final BufferedReader reader;

    public TaskCLI(TaskService service) {
        this.service = service;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        println("Task CLI - type 'help' for commands");
        while (true) {
            print("\n> ");
            String line = readLine();
            if (line == null) {
                break;
            }
            String[] parts = line.trim().split("\\s+", 2);
            if (parts.length == 0 || parts[0].isEmpty()) {
                continue;
            }
            String cmd = parts[0].toLowerCase();
            String rest = parts.length > 1 ? parts[1] : "";

            switch (cmd) {
                case "help":
                    showHelp();
                    break;
                case "create":
                case "add":
                    handleCreate(rest);
                    break;
                case "list":
                    handleList();
                    break;
                case "update":
                    handleUpdate(rest);
                    break;
                case "delete":
                case "remove":
                    handleDelete(rest);
                    break;
                case "exit":
                case "quit":
                    println("Goodbye!");
                    return;
                default:
                    println("Unknown command. Type 'help' for available commands.");
            }
        }
    }

    private void handleCreate(String rest) {
        String title = rest == null ? "" : rest.trim();
        if (title.isEmpty()) {
            title = prompt("Enter title: ");
        }
        if (title == null || title.trim().isEmpty()) {
            println("Title cannot be empty.");
            return;
        }
        Task task = service.addTask(title.trim());
        println("Created: " + task);
    }

    private void handleList() {
        List<Task> tasks = service.listTasks();
        if (tasks.isEmpty()) {
            println("No tasks yet.");
            return;
        }
        tasks.stream()
                .sorted(Comparator.comparing(Task::getId))
                .forEach(t -> println(t.toString()));
    }

    private void handleUpdate(String rest) {
        String[] args = (rest == null ? "" : rest.trim()).split("\\s+", 2);
        String idStr = args.length > 0 ? args[0] : "";
        String statusStr = args.length > 1 ? args[1] : "";

        if (idStr.isEmpty()) {
            idStr = prompt("Enter task id: ");
        }
        long id;
        try {
            id = Long.parseLong(idStr.trim());
        } catch (NumberFormatException e) {
            println("Invalid id.");
            return;
        }

        if (statusStr.isEmpty()) {
            println("Choose status: pending | in_progress | completed");
            statusStr = prompt("Enter status: ");
        }
        TaskStatus status = TaskStatus.fromString(statusStr);
        if (status == null) {
            println("Invalid status. Use: pending | in_progress | completed");
            return;
        }
        service.updateStatus(id, status).ifPresentOrElse(
                task -> println("Updated: " + task),
                () -> println("Task not found: " + id)
        );
    }

    private void handleDelete(String rest) {
        String idStr = (rest == null ? "" : rest.trim());
        if (idStr.isEmpty()) {
            idStr = prompt("Enter task id: ");
        }
        long id;
        try {
            id = Long.parseLong(idStr.trim());
        } catch (NumberFormatException e) {
            println("Invalid id.");
            return;
        }
        boolean removed = service.deleteTask(id);
        if (removed) {
            println("Deleted task #" + id);
        } else {
            println("Task not found: " + id);
        }
    }

    private void showHelp() {
        println("Commands:");
        println("  help                         Show this help message");
        println("  create <title>              Create a new task");
        println("  list                        List all tasks");
        println("  update <id> <status>        Update status (pending|in_progress|completed)");
        println("  delete <id>                 Delete a task by id");
        println("  exit                        Quit the program");
    }

    private String prompt(String message) {
        print(message);
        return readLine();
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            println("Error reading input. Exiting.");
            return null;
        }
    }

    private void println(String s) {
        System.out.println(s);
    }

    private void print(String s) {
        System.out.print(s);
    }
}



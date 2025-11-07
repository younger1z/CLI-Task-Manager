package com.task_cli.taskcli;

public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        TaskCLI cli = new TaskCLI(service);
        cli.start();
    }
}


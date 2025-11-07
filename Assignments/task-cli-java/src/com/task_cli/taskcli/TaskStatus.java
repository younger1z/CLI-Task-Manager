package com.task_cli.taskcli;

public enum TaskStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String input) {
        if (input == null) {
            return null;
        }
        String normalized = input.trim().toUpperCase().replace('-', '_').replace(' ', '_');
        switch (normalized) {
            case "PENDING":
                return PENDING;
            case "IN_PROGRESS":
                return IN_PROGRESS;
            case "COMPLETED":
                return COMPLETED;
            default:
                return null;
        }
    }
}



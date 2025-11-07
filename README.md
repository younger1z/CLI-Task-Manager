# Task CLI (Java, OOP)

A simple command-line task manager written in Java to practice Object-Oriented Programming. It supports creating tasks, listing, updating status, deleting, and exiting.

## Requirements
- Java 11+ (any modern JDK should work)
- Bash (for the helper scripts)

## Project Structure
```
/task-cli-java
  ├─ src/com/task-cli/taskcli/
  │   ├─ Main.java
  │   ├─ Task.java
  │   ├─ TaskStatus.java
  │   ├─ TaskRepository.java
  │   └─ TaskService.java
  │   └─ TaskCLI.java
  ├─ compile.sh
  ├─ run.sh
  └─ .gitignore
```

## Build & Run
```bash
# From the project root
bash compile.sh
bash run.sh
```

## Usage
Type `help` to see commands.
- `create <title>`: Create a task. If `<title>` is omitted, you will be prompted.
- `list`: Show all tasks.
- `update <id> <status>`: Update status to one of `pending`, `in_progress`, `completed`.
- `delete <id>`: Delete a task by id.
- `exit`: Quit the program.

Examples:
```text
> create Read OOP chapter
Created: #1 | PENDING | Read OOP chapter
> list
#1 | PENDING | Read OOP chapter
> update 1 completed
Updated: #1 | COMPLETED | Read OOP chapter
> delete 1
Deleted task #1
> exit
```

## OOP Design
- `Task`: Domain model with id, title, status, timestamps.
- `TaskStatus`: Enum of valid states.
- `TaskRepository`: In-memory persistence and id generation.
- `TaskService`: Business logic façade.
- `TaskCLI`: CLI controller/IO loop.
- `Main`: Bootstrap wiring.

## Push to GitHub
```bash
cd /home/ab07/Documents/Assignments/task-cli-java
git init
git add .
git commit -m "Initial commit: Java Task CLI"
# Create a repo on GitHub named task-cli-java, then add it as origin:
# Replace <YOUR_USERNAME> accordingly
git branch -M main
git remote add origin git@github.com:<YOUR_USERNAME>/task-cli-java.git
# or: git remote add origin https://github.com/<YOUR_USERNAME>/task-cli-java.git

git push -u origin main
```

## Notes
- Data is in-memory only; restarting clears tasks.
- Tested on Linux; should work anywhere with a JDK.

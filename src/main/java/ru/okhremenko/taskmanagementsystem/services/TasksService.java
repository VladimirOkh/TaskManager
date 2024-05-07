package ru.okhremenko.taskmanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.okhremenko.taskmanagementsystem.models.Task;
import ru.okhremenko.taskmanagementsystem.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    private final TaskRepository taskRepository;

    @Autowired
    public TasksService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTasksByPerson(int id) {
        return taskRepository.findAllByAssignedToId(id);
    }

}
package ru.okhremenko.taskmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.okhremenko.taskmanagementsystem.models.Person;
import ru.okhremenko.taskmanagementsystem.models.Task;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findAllByAssignedToId(int id);
}

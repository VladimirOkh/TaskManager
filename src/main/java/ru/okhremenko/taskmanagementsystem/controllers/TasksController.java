package ru.okhremenko.taskmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.okhremenko.taskmanagementsystem.models.Person;
import ru.okhremenko.taskmanagementsystem.models.Task;
import ru.okhremenko.taskmanagementsystem.repositories.PeopleRepository;
import ru.okhremenko.taskmanagementsystem.repositories.TaskRepository;
import ru.okhremenko.taskmanagementsystem.services.PersonDetailsService;
import ru.okhremenko.taskmanagementsystem.services.TasksService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;
    private final PersonDetailsService personDetailsService;
    private final PeopleRepository peopleRepository;

    @Autowired
    public TasksController(TasksService tasksService, PersonDetailsService personDetailsService, PeopleRepository peopleRepository) {
        this.tasksService = tasksService;
        this.personDetailsService = personDetailsService;
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/index")
    public String index() {
        return "tasks/index";
    }


    @GetMapping("/show")
    public String showUserTasks(Model model, Principal principal) {
        String username = principal.getName(); // Получаем имя текущего пользователя
        Person person = (Person) personDetailsService.loadUserByUsername(username); // Предполагается, что у вас есть сервис для работы с пользователями и метод для поиска по имени
        Optional<Task> tasks = tasksService.findTasksByPerson(person.getId()); // Получаем задачи, назначенные конкретному пользователю
        model.addAttribute("tasks", tasks);
        return "tasks/show";
    }

    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/new";
    }

    @PostMapping()
    public String createTask(@ModelAttribute("task") Task task, RedirectAttributes redirectAttributes) {
        tasksService.save(task);
        redirectAttributes.addFlashAttribute("successMessage", "Задача успешно создана!");
        return "redirect:/tasks/list";
    }
}
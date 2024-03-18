package com.example.demo.controller;

import com.example.demo.entity.Record;
import com.example.demo.entity.Task;
import com.example.demo.service.RecordService;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private final TaskService taskService;
    @Autowired
    private final RecordService recordService;

    @GetMapping("/{recordId}")
    public String showTask(
                           @PathVariable("recordId") Integer recordId,
                           Model model) {
        List<Task> tasks = taskService.getAllTasksByRecordId(recordId);
        Record record = recordService.getRecordById(recordId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("record", record);
        model.addAttribute("user", record.getUser());
        return "/task-list";
    }

    @GetMapping("/{recordId}/create")
    public String formTask(
            @PathVariable("recordId") Integer recordId,
            Model model
    ){
        Task task = new Task();
        Record record = recordService.getRecordById(recordId);


        model.addAttribute("user",record.getUser().getId());
        model.addAttribute("record", recordId);
        model.addAttribute("task", task);
        return "task-form";
    }

    @PostMapping("/{recordId}/create")
    public String saveTask(
            @PathVariable("recordId") Integer recordId,
            @ModelAttribute("task") Task task){
        Record record = recordService.getRecordById(recordId);
        task.setRecord(record);



        taskService.save(task);
        return "redirect:/task/"+ recordId;
    }

    @GetMapping("/{recordId}/edit/{taskId}")
    public String editForm(@PathVariable("recordId") Integer recordId,@PathVariable("taskId") Integer taskId,Model model){
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("record", recordId);
        return "task-form";
    }

    @PostMapping("/{recordId}/edit/{taskId}")
    public String saveEdit(@PathVariable("recordId") Integer recordId,@PathVariable("taskId") Integer taskId,@ModelAttribute("task") Task task){
        Task oldTask = taskService.getTaskById(taskId);
        if(oldTask != null){
            oldTask.setDescription(task.getDescription());
            taskService.save(oldTask);
        }
        return "redirect:/task/" + recordId;
    }

    @GetMapping("/{recordId}/delete/{taskId}")
    public String deleteTask(@PathVariable("recordId") Integer recordId, @PathVariable("taskId") Integer taskId) {
        if (taskService.taskExist(taskId)) {
            taskService.delete(taskId);
        }
        return "redirect:/task/" + recordId;
    }
}

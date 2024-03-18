package com.example.demo.service.imp;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository repository;

    @Override
    public List<Task> getAllTasksByRecordId(Integer recordId) {
        return repository.findAllByRecordId(recordId);
    }

    @Override
    public void save(Task task) {
        repository.save(task);
    }

    @Override
    public Task getTaskById(Integer taskId) {
        return repository.findById(taskId).get();
    }

    @Override
    public boolean taskExist(Integer taskId) {
        return repository.existsById(taskId);
    }

    @Override
    public void delete(Integer taskId) {
        repository.deleteById(taskId);
    }
}

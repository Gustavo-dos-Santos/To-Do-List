package com.example.demo.service;


import com.example.demo.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasksByRecordId(Integer recordId);

    void save(Task task);

    Task getTaskById(Integer taskId);

    boolean taskExist(Integer taskId);

    void delete(Integer taskId);
}

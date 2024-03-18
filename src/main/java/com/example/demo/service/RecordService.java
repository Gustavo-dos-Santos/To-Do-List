package com.example.demo.service;

import com.example.demo.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecordsByUserId(Integer userId);

    void save(Record newRecord);

    Record getRecordById(Integer recordId);

    boolean recordExist(Integer recordId);

    void delete(Integer recordId);

}

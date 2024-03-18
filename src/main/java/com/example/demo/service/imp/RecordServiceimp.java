package com.example.demo.service.imp;

import com.example.demo.entity.Record;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.RecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class RecordServiceimp implements RecordService {

    @Autowired
    private final RecordRepository repository;
    public List<Record> getAllRecordsByUserId(Integer userId) {
        return repository.findAllRecordsByUserId(userId);
    }

    public void save( Record newRecord) {repository.save(newRecord);
    }

    @Override
    public Record getRecordById(Integer recordId) {
        return repository.findById(recordId).get();
    }

    @Override
    public boolean recordExist(Integer recordId) {
        return repository.existsById(recordId);
    }

    @Override
    public void delete(Integer recordId) {
        repository.deleteById(recordId);
    }

}

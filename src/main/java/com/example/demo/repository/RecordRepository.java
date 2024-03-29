package com.example.demo.repository;

import com.example.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer > {

    List<Record> findAllRecordsByUserId(Integer userId);
}

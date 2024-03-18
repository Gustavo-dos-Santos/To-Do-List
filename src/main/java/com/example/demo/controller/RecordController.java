package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.RecordService;
import com.example.demo.service.UserService;
import org.springframework.ui.Model;
import com.example.demo.entity.Record;
import com.example.demo.service.imp.RecordServiceimp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private final RecordService recordService;
    @Autowired
    private final UserService userService;
    @GetMapping("/{userId}")
    public String showAnotations(@PathVariable("userId") Integer userId, Model model){
        List<Record> records = recordService.getAllRecordsByUserId(userId);
        model.addAttribute("user", userId);
        model.addAttribute("records", records);
        return "record-page";
    }

    @GetMapping("/{userId}/create")
    public String recordForm(@PathVariable("userId") Integer userId, Model model){
        Record record = new Record();
        model.addAttribute("user", userId);
        model.addAttribute("record", record);
        return "record-form";
    }

    @PostMapping("/{userId}/create")
    public String saveRecord(@PathVariable("userId") Integer userId,@ModelAttribute("record") Record newRecord){
        User user = userService.getUser(userId);
        newRecord.setUser(user);
        recordService.save(newRecord);
        return "redirect:/record/"+userId;
    }

    @GetMapping("/{userId}/edit/{recordId}")
    public String preEdit(@PathVariable("userId") Integer userId, @PathVariable("recordId") Integer recordId,Model model){
        model.addAttribute("record", recordService.getRecordById(recordId));
        model.addAttribute("user", userId);
        return  "record-form";
    }

    @PostMapping("/{userId}/edit/{recordId}")
    public String saveEdit(@ModelAttribute("record") Record record, @PathVariable("userId") Integer userId, @PathVariable("recordId") Integer recordId){
        Record oldRecord = recordService.getRecordById(recordId);
        if(oldRecord != null){
            oldRecord.setTitle(record.getTitle());
            recordService.save(oldRecord);
        }

        return  "redirect:/record/"+userId;
    }

    @GetMapping("/{userId}/delete/{recordId}")
    public String deleteRecord(@PathVariable("userId") Integer userId, @PathVariable("recordId") Integer recordId){
        if(recordService.recordExist(recordId)){
           recordService.delete(recordId);
        }
        return  "redirect:/record/"+userId;
    }

}

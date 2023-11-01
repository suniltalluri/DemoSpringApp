package com.Security.Security.controller;

import com.Security.Security.entity.Tasks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {
    @GetMapping("allTasks")
    public String getAllTasks(Tasks tasks){
        return "all tasks.....";
    }
}

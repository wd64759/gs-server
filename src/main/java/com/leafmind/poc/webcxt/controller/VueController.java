package com.leafmind.poc.webcxt.controller;

import com.leafmind.poc.webcxt.models.JiraTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/vue")
public class VueController {

    List<JiraTask> fooTasks = new ArrayList<>();

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<JiraTask> getList() {
        log.info(">>> get request for getList()");
        if(fooTasks.size() == 0) {
            fooTasks.addAll(createFooTasks(5));
        }
        return fooTasks;
    }

    @PostMapping("/add")
    public boolean add(@RequestBody JiraTask newTask) {
        JiraTask dupTask = fooTasks.stream()
                .filter(task -> task.equals(newTask))
                .findAny().orElse(null);

        if(dupTask == null) {
            fooTasks.add(newTask);
            return true;
        }
        log.info("duplicated with existing task: " + dupTask);
        return false;
    }

    private List<JiraTask> createFooTasks(int size) {
        List<JiraTask> tasks = new ArrayList<>();
        for(int i=0;i<size;i++) {
            JiraTask task = new JiraTask();
            task.setId(String.valueOf(i));
            task.setName(String.format("JIRA-%02d", i));
            task.setStatus("open");
            task.setUpdated(new Date());
            tasks.add(task);
        }
        return tasks;
    }
}

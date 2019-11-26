package com.leafmind.poc.webcxt.controller;

import com.leafmind.poc.webcxt.models.JiraTask;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vue")
public class VueController {

    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(VueController.class);
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

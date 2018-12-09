package name.roman.example.presenterspringboot.scheduler.service;

import lombok.extern.java.Log;
import name.roman.example.presenterspringboot.scheduler.domain.Displayable;
import name.roman.example.presenterspringboot.scheduler.task.DisplayListTask;
import name.roman.example.presenterspringboot.scheduler.task.DisplayListTaskTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Component
@Log
public class ScheduleService {

    private final TaskScheduler taskScheduler;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public ScheduleService(TaskScheduler taskScheduler, JmsTemplate jmsTemplate) {
        this.taskScheduler = taskScheduler;
        this.jmsTemplate = jmsTemplate;
    }

    public void reload() {
        // Sample data. Should be formed elsewhere.
        final List<Displayable> displayableList = Arrays.asList(
                new Displayable("WallPaper - One", 1000),
                new Displayable("WallPaper - Two", 2000),
                new Displayable("WallPaper - Three", 3000));

        // This is a Runnable that traverse through a list of displayables each run.
        final DisplayListTask displayListTask = new DisplayListTask(displayableList, jmsTemplate);

        // Set up a scheduled task with
        final ScheduledFuture repeatingListA = taskScheduler.schedule(displayListTask,
                new DisplayListTaskTrigger(new Date(), null, displayListTask));
    }
}

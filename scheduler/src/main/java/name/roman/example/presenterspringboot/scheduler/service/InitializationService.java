package name.roman.example.presenterspringboot.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Lazy(false)
public class InitializationService {

    private final ScheduleService scheduleService;

    @Autowired
    public InitializationService(final ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostConstruct
    public void postConstruct() {
        scheduleService.reload();
    }
}

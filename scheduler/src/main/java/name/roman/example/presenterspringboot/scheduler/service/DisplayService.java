package name.roman.example.presenterspringboot.scheduler.service;

import name.roman.example.presenterspringboot.scheduler.event.DisplayNextCommand;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DisplayService {

    private static final Logger log = Logger.getLogger(DisplayService.class.getName());
    private String current = null;

    @JmsListener(destination = "DisplayNextCommand")
    public void displayNext(DisplayNextCommand displayNextCommand) {
        if (current != null) {
            log.info(() -> "Stopping current: " + current);
        }
        log.info(() -> "Displaying next: " + displayNextCommand.getPath());
        current = displayNextCommand.getPath();
    }

}

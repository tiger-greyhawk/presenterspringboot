package name.roman.example.presenterspringboot.scheduler.task;

import lombok.extern.java.Log;
import name.roman.example.presenterspringboot.scheduler.domain.Displayable;
import name.roman.example.presenterspringboot.scheduler.event.DisplayNextCommand;
import org.springframework.jms.core.JmsTemplate;

import java.text.MessageFormat;
import java.util.List;

@Log
public class DisplayListTask implements Runnable {

    // map of path to delay
    private final List<Displayable> displayables;
    private final JmsTemplate jmsTemplate;

    private int last = 0;

    public DisplayListTask(List<Displayable> displayables, JmsTemplate jmsTemplate) {
        this.displayables = displayables;
        this.jmsTemplate = jmsTemplate;
    }

    public Displayable getCurrent() {
        return displayables.get(last);
    }

    public Displayable getPrevious() {
        return displayables.get(last == 0 ? displayables.size() - 1 : last - 1);
    }

    @Override
    public void run() {
        log.info(() -> MessageFormat.format("Displaying {0} of \"{1}\" items: \"{2}\"", last, displayables.size(), getCurrent().getPath()));
        jmsTemplate.convertAndSend("DisplayNextCommand", new DisplayNextCommand(getCurrent().getPath()));
        last = (last + 1) % displayables.size();
    }
}

package name.roman.example.presenterspringboot.scheduler.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Displayable {
    private String path;
    private long delay;
}

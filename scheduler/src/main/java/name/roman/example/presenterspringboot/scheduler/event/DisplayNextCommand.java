package name.roman.example.presenterspringboot.scheduler.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DisplayNextCommand implements Serializable {
    private String path;
}

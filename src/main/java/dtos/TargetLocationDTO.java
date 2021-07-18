package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetLocationDTO {
    private boolean status;
    private boolean firstChoice;
    private int id;
    private String name;
}

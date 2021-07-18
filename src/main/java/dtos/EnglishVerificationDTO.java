package dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnglishVerificationDTO {
    private String status;
    private int timeToStartAgain;
}

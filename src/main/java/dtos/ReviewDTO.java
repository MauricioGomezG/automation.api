package dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewDTO {
    private int lastReviewStatus;
    private String lastReviewStatusText;
    private  int lastReviewId;
    private String reviewCreatedOn;
    private List<String> notes;
    private boolean show;
    private boolean canRequestReview;
    private int daysUntilNextReviewAvailable;
    private boolean isUnderReview;

    public boolean getIsUnderReview() {
        return isUnderReview;
    }

    public void setIsUnderReview(boolean underReview) {
        isUnderReview = underReview;
    }
}

package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileDTO extends BaseDTO {
    private double id;
    private String firstName;
    private String lastName;
    private String email;
    private String resume;
    private String image;
    private int citizenship;
    private int location;
    private String countryOrigin;
    private String countryLocation;
    private int gender;
    private String pronoun;
    private int phone;
    private boolean isPhoneConfirmed;
    private OnboardingDTO onboarding;
    private int positionId;
    private String positionName;
    private int yearsOfExperience;
    private boolean openForDifferentRole;
    private List<UserPositionDTO> userPositions;
    private List<SkillDTO> topSkill;
    private List<SkillDTO> secondarySkill;
    private int openForRemoteJobs;
    private ItemDTO targetLocation;
    private String firstChoice;
    private int salaryExpectationCad;
    private int salaryExpectationEur;
    private int salaryExpectationUsd;
    private int salaryRangeCad;
    private int salaryRangeEur;
    private int salaryRangeUsd;
    private int salaryRangeRemote;
    private int visaStatusCanadian;
    private int visaStatusEU;
    private int visaStatusUs;
    private String passportExpirationDate;
    private boolean passportValid;
    private boolean isTech;
    private boolean leadershipExperience;
    private int leadershipYears;
    private List<CompanySizeDTO> companySize;
    private boolean activelyLookingForJob;
    private int noticePeriod;
    @JsonProperty(value = "linkedin")
    private String linkedIn;
    private String git;
    private String webSite;
    private int degreeType;
    private EnglishVerificationDTO englishVerification;
    private ReviewDTO review;
    private String englishLevel;
    private boolean englishVideoIsPublic;

    public boolean getIsTech() {
        return isTech;
    }

    public void setIsTech(boolean tech) {
        isTech = tech;
    }

    public boolean getIsPhoneConfirmed() {
        return isPhoneConfirmed;
    }

    public void setIsPhoneConfirmed(boolean phoneConfirmed) {
        isPhoneConfirmed = phoneConfirmed;
    }

    public boolean getLeadershipExperience() {
        return leadershipExperience;
    }

    public void setLeadershipExperience(boolean leadershipExperience) {
        this.leadershipExperience = leadershipExperience;
    }

    public int getLeadershipYears() {
        return leadershipYears;
    }

    public void setLeadershipYears(int leadershipYears) {
        this.leadershipYears = leadershipYears;
    }

    public String getLinkedin() {
        return linkedIn;
    }

    public void setLinkedin(String linkedIn) {
        this.linkedIn = linkedIn;
    }
}

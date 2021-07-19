package onboarding;

import models.Profile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.AuthException;
import texts.StaticTexts;


public class TestOnboarding {

    private String userUnderTest = "registeredUser1";
    private Profile profile;

    @BeforeClass
    public void precondition() {
        profile = new Profile(userUnderTest);
    }

    @Test(enabled=false)
    public void hearFromTest() throws AuthException {
        String parameter = "hearFrom";
        profile.updateParameter(parameter, "Youtube");
        profile.updateParameter(parameter, "Linkedin");
        profile.updateParameter(parameter, "Twitter");
        profile.updateParameter(parameter, "Facebook");
        profile.updateParameter(parameter, "Instagram");
        profile.updateParameter(parameter, "Vanhack PodCast");
        profile.updateParameter(parameter, "Vanhack Website");
        profile.updateParameter(parameter, "From a Vanhack employee");
        profile.updateParameter(parameter, "From a friend");
        profile.updateParameter(parameter, "Google Search");
        profile.updateParameter(parameter, "Meetup");
        profile.updateParameter(parameter, "Conference");
        profile.updateParameter(parameter, "My imaginary friend");
        profile.updateParameter(parameter, "!\"#$%&/()O=?¡[]¨*ñ,._;:");
    }

    @Test()
    public void selectingRoleTest() throws AuthException {
        String parameter = "positionId";
        profile.updateParameter(parameter, 1);
        Assert.assertEquals(profile.getProfile().getPositionId(), 1, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, 19);
        Assert.assertEquals(profile.getProfile().getPositionId(), 19, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, 38);
        Assert.assertEquals(profile.getProfile().getPositionId(), 38, StaticTexts.parameterDontMatchProfile(parameter));
    }

    @Test()
    public void yearsOfExperienceTest() throws AuthException {
        String parameter = "yearsOfExperience";
        profile.updateParameter(parameter, 1);
        Assert.assertEquals(profile.getProfile().getYearsOfExperience(), 1, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, 10);
        Assert.assertEquals(profile.getProfile().getYearsOfExperience(), 10, StaticTexts.parameterDontMatchProfile(parameter));
    }

    @Test
    public void leadershipExperienceTest() throws AuthException {
        String parameter = "leadershipExperience";
        profile.updateParameter(parameter, true);
        Assert.assertEquals(profile.getProfile().getLeadershipExperience(), true, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, false);
        Assert.assertEquals(profile.getProfile().getLeadershipExperience(), false, StaticTexts.parameterDontMatchProfile(parameter));
    }

    @Test()
    public void leaderShipYearsTest() throws AuthException {
        String parameter = "leadershipYears";
        profile.updateParameter(parameter, 1);
        Assert.assertEquals(profile.getProfile().getLeadershipYears(), 1, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, 5);
        Assert.assertEquals(profile.getProfile().getLeadershipYears(), 5, StaticTexts.parameterDontMatchProfile(parameter));
        profile.updateParameter(parameter, 10);
        Assert.assertEquals(profile.getProfile().getLeadershipYears(), 10, StaticTexts.parameterDontMatchProfile(parameter));
    }

//    @Test()
//    public void alwaysFailTest(){
//        Assert.assertTrue(false);
//    }


}

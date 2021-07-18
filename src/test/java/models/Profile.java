package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import common.Service;
import dtos.ProfileDTO;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import utils.AuthException;

@Getter
@Setter
public class Profile extends BaseModel {
    private String user;
    private ProfileDTO profile;

    public Profile(String user) {
        this.user = user;
    }

    public void getUserProfile() throws AuthException {
        Response response = Service.access().getProfile(user);
        Assert.assertTrue(isSuccessfulResponse(response), "Error getting profile");
        try {
            JsonNode root = mapper.readTree(response.getBody().asString());
            JsonNode result = root.get("result");
            this.profile = mapper.treeToValue(result, ProfileDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateParameter(String key, String value) throws AuthException {
        Response response = Service.access().updateProfile(this.user, generateKeyValueRequest(key, value));
        Assert.assertTrue(isSuccessfulResponse(response), String.format("Error updating profile with \"%s\" parameter", key));
        getUserProfile();
        logger.info(String.format("%s updated to %s in user's profile", key, value));
    }

    public void updateParameter(String key, int value) throws AuthException {
        Response response = Service.access().updateProfile(this.user, generateKeyValueRequest(key, value));
        Assert.assertTrue(isSuccessfulResponse(response), String.format("Error updating profile with \"%s\" parameter", key));
        getUserProfile();
        logger.info(String.format("%s updated to %s in user's profile", key, value));
    }

    public void updateParameter(String key, boolean value) throws AuthException {
        Response response = Service.access().updateProfile(this.user, generateKeyValueRequest(key, value));
        Assert.assertTrue(isSuccessfulResponse(response), String.format("Error updating profile with \"%s\" parameter", key));
        getUserProfile();
        logger.info(String.format("%s updated to %s in user's profile", key, value));
    }

}

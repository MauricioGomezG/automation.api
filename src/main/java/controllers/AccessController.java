package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utils.AuthException;
import utils.FileManager;

public class AccessController extends BaseController {
    @Override
    protected String getBaseURL() {
        return FileManager.getUserConfigProperty("baseUrl") + "/access/v2";
    }

    public Response getProfile(String user) throws AuthException {
        Response response =
                this
                        .withUser(user)
                        .withDefaultHeaders()
                        .withUrl(String.format("%s/profile", getBaseURL()))
                        .withMethod(Method.GET)
                        .doRequest();
        return response;
    }

    public Response updateProfile(String user, ObjectNode body) throws AuthException {
        Response response =
                this
                        .withUser(user)
                        .withDefaultHeaders()
                        .withUrl(String.format("%s/profile", getBaseURL()))
                        .withMethod(Method.POST)
                        .withBody(body)
                        .doRequest();
        return response;
    }
}

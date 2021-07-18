package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.AuthException;
import utils.Authenticator;

public abstract class BaseController<T> {
    protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    private String requestUrl;
    private Method requestMethod;

    public BaseController() {
        this.requestSpecBuilder.setUrlEncodingEnabled(false);
    }

    public BaseController withDefaultHeaders() {
        this.requestSpecBuilder.addHeader("Content-Type", "application/json");
        return this;
    }

    public BaseController withHeader(String headerKey, String headerValue) {
        this.requestSpecBuilder.addHeader(headerKey, headerValue);
        return this;
    }

    public BaseController withBody(String requestBody) {
        this.requestSpecBuilder.setBody(requestBody);
        return this;
    }

    public BaseController withBody(ObjectNode requestBody) {
        this.requestSpecBuilder.setBody(requestBody);
        return this;
    }

    public BaseController withMethod(Method method) {
        this.requestMethod = method;
        return this;
    }

    public BaseController withUrl(String url) {
        this.requestUrl = url;
        return this;
    }

    public BaseController withQueryParam(String parameterName, String parameterValue) {
        this.requestSpecBuilder.addQueryParam(parameterName, new Object[]{parameterValue});
        return this;
    }

    public BaseController withUser(String user) throws AuthException {
        String token = Authenticator.getInstance().getAccessToken(user);
        this.requestSpecBuilder.addHeader("Cookie", token);
        return this;
    }

    public Response doRequest() {
        Response response = (Response)RestAssured.given().spec(this.requestSpecBuilder.build()).request(this.requestMethod, this.requestUrl, new Object[0]);
        this.init();
        return response;
    }

    public void init() {
        this.requestSpecBuilder = new RequestSpecBuilder();
        this.requestUrl = null;
        this.requestMethod = null;
    }

    protected abstract String getBaseURL();
}

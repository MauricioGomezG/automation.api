package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.BaseController;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class Authenticator extends BaseController {
    private static volatile Authenticator instance;
    private static final Object helper = new Object();
    private final TokenManager tokenManager = new TokenManager();

    public static Authenticator getInstance() {
        Authenticator result = instance;
        if (result == null) {
            synchronized(helper) {
                result = instance;
                if (result == null) {
                    instance = result = new Authenticator();
                }
            }
        }

        return result;
    }

    public synchronized String getAccessToken(String user) throws AuthException {
        String validToken = this.tokenManager.getValidToken(user);
        if (validToken == null) {
            validToken = this.createAccessToken(user);
            this.tokenManager.addToken(user, validToken);
        }

        return validToken;
    }

    private String createAccessToken(String user) throws AuthException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("userNameOrEmailAddress", this.getUsername(user));
        requestBody.put("password", this.getPassword(user));
        Response response =
                this
                        .withDefaultHeaders()
                        .withBody(requestBody.toString())
                        .withMethod(Method.POST)
                        .withUrl(getBaseURL())
                        .doRequest();

        response.getBody().prettyPeek();
        if (response.getStatusCode() != 200) {
            throw new AuthException((String) response.body().path("message", new String[0]));
        } else {
            return createCookie((String) response.body().path("result.accessToken", new String[0]));

        }
    }

    private String createCookie(String accessToken){
        String[] pieces = accessToken.split("\\.");
        return String.format("2a17a939=%s; d449d1f4=%s; c655851d=%s;", pieces[0], pieces[1], pieces[2]);
    }

    private String getUsername(String user) throws AuthException {
        String username = FileManager.getUserConfigProperty(user);
        if (username == null) {
            throw new AuthException("Unknown user (" + user + ")");
        } else {
            return username;
        }
    }

    private String getPassword(String user) throws AuthException {
        String password = FileManager.getUserConfigProperty(user + ".password");
        if (password == null) {
            throw new AuthException("Unknown user (" + user + ")");
        } else {
            return password;
        }
    }

    @Override
    protected String getBaseURL() {
        return FileManager.getUserConfigProperty("baseUrl") + "/access/v1/auth/Authenticate";
    }
}

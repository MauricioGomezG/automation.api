package utils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private Map<String, String> tokens = new HashMap();
    private Map<String, Instant> timeElapsed = new HashMap();

    public String getValidToken(String user) {
        String result = null;
        if (this.tokens.containsKey(user)) {
            long minutesElapsed = Duration.between((Temporal)this.timeElapsed.get(user), Instant.now()).toMinutes();
            if (minutesElapsed < 24) {
                result = (String)this.tokens.get(user);
            }
        }

        return result;
    }

    public void addToken(String user, String token) {
        if (!this.tokens.containsKey(user) || !((String)this.tokens.get(user)).equals(token)) {
            this.tokens.put(user, token);
            this.timeElapsed.put(user, Instant.now());
        }
    }
}
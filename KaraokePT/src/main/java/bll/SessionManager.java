package bll;

public class SessionManager {
    private static SessionManager instance;
    private String currentUser;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    public String getCurrentUser() {
        return currentUser;
    }
    public void clearSession() {
        this.currentUser = null;
    }
}

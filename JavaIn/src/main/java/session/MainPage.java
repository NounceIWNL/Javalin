package session;

public class MainPage {
    private String currentUser;

    public MainPage(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }
} 
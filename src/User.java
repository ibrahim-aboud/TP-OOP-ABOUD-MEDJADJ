import java.util.Objects;

public class User {
    private String pseudo, mdp;
    private boolean connected;

    public User(String pseudo, String mdp) {
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(pseudo, user.pseudo);
    }

    public boolean isCorrectPassword(String password) {
        return password.equals(mdp);
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected() {
        return connected;
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(pseudo, mdp, connected);
//    }
}
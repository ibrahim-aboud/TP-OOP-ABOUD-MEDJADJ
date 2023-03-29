import java.util.ArrayList;

public class App {
    private ArrayList<User> users = new ArrayList<User>();
    User activeUser;

    public void signup(String pseudo, String mdp) {
        User u = new User(pseudo, mdp);
        if (!users.contains(u)) {
            users.add(u);
            System.out.println("Utilisateur inscrit avec succès!");
        }
        else System.out.println("ERREUR: Utilisateur deja existant!");
    }

    public void login(String pseudo, String mdp) {
        User u = new User(pseudo, mdp);
        if (activeUser !=  null && !activeUser.equals(u)) activeUser.setConnected(false);
        if (users.contains(u)) {
            u = users.get(users.indexOf(u));
            if (u.isCorrectPassword(mdp)) {
                u.setConnected(true);
                activeUser = u;
                System.out.println("Utilisateur connecté avec succès!");
            }
            else System.out.println("ERREUR: Mot de passe erroné!");
        }
        else System.out.println("ERREUR: Utilisateur inexistant, veuillez vous inscrire!");

//        for (User us : users) {
//            System.out.print(us.isConnected()+", ");
//        }
//        System.out.println(" ");
    }
}

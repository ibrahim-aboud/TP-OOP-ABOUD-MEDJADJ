public class Main {
    public static void main(String[] args) {
        App app = new App();

        app.signup("ok", "123");
        app.signup("okkk", "12");
        app.signup("ok", "123");
        app.login("okkkk", "123");
        app.login("ok", "123");
        app.login("okkk", "123");
        app.login("okkk", "12");
    }
}
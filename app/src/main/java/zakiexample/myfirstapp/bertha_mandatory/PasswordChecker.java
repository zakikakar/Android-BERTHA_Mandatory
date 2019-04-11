package zakiexample.myfirstapp.bertha_mandatory;

public class PasswordChecker {

    // This method needs to do some proper password checking
    public static boolean Check(String username, String password) {
        return password.startsWith("a");
    }
}

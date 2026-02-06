// With Exception Handling 

import java.util.Scanner;

/* ---------- Custom Exceptions ---------- */

class WeakPasswordException extends Exception {
    public WeakPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {
    public AccountLockedException(String message) {
        super(message);
    }
}

/* ---------- User Class ---------- */

class User {

    private String username;
    private String password;
    private int failedAttempts;
    private boolean isLocked;

    // Constructor with exception
    public User(String username, String password) throws WeakPasswordException {

        if (!isStrongPassword(password)) {
            throw new WeakPasswordException(
                "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character."
            );
        }

        this.username = username;
        this.password = password;
        this.failedAttempts = 0;
        this.isLocked = false;
    }

    // Static password strength checker
    public static boolean isStrongPassword(String password) {

        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch))
                hasUpper = true;
            else if (Character.isLowerCase(ch))
                hasLower = true;
            else if (Character.isDigit(ch))
                hasDigit = true;
            else
                hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    // Login method with exception
    public void login(String enteredPassword) throws AccountLockedException {

        if (isLocked) {
            throw new AccountLockedException(
                "Account is already locked due to multiple failed login attempts."
            );
        }

        if (password.equals(enteredPassword)) {
            System.out.println("Login successful!");
            failedAttempts = 0;
        } else {
            failedAttempts++;
            System.out.println("Wrong password.");

            if (failedAttempts >= 3) {
                isLocked = true;
                throw new AccountLockedException(
                    "Account locked after 3 failed login attempts."
                );
            }
        }
    }
}

/* ---------- Main Class ---------- */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        User user = null;
        String password;

        // Registration loop
        while (true) {
            System.out.print("Set password: ");
            password = sc.nextLine();

            try {
                user = new User(username, password);
                System.out.println("User registered successfully.\n");
                break;
            } catch (WeakPasswordException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        // Login attempts
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter password to login: ");
            String enteredPassword = sc.nextLine();

            try {
                user.login(enteredPassword);
            } catch (AccountLockedException e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        sc.close();
    }
}

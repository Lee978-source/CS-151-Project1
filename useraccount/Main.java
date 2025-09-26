/**
 * @author [Lordin Yi]
 * @version 1.0
 * CS151 Fall 2025 - Project 1
 */

package useraccount;

public class Main {
    public static void main(String[] args) {
        // Create one demo account (constructor also creates its Drive)
        AccountManager acc = new AccountManager(
                "Alice Nguyen",
                "alice@gmail.com",
                "secret123",
                "01/01/2000"
        );

        // To simulate a login
        // on success it will print the account menu
        acc.login("alice@gmail.com", "secret123");

        // call menu directly to always show it:
        // acc.accountMenu();

        System.out.println("\n--- Demo finished ---");
    }
}


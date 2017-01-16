package by.saidanov.bank;

import by.saidanov.bank.exceptions.TermCanNotRaiseException;
import by.saidanov.bank.utility.menu.CustomMenu;

import java.io.IOException;

/**
 * The main class.
 */
public class BankRunner {

    public static void main(String[] args) throws IOException, TermCanNotRaiseException {

        CustomMenu menu = new CustomMenu();
        menu.start();
    }
}

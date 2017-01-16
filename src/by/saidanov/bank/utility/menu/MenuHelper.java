package by.saidanov.bank.utility.menu;

import by.saidanov.bank.beans.Manager;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.utility.serialization.Serialization;
import by.saidanov.bank.utility.serialization.SerializationHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.TreeSet;

/**
 * MenuHelper
 *
 * @version 1.1
 *
 * Date 13.01.2017
 */
final class MenuHelper {

    static void workInApp(BufferedReader caseReader) throws IOException {
        System.out.println("You are in main menu. Please enter a base command or enter \"help\" to see all base commands.");
        int accountId;
        String userInput;
        String[] splitedUserInput;
        int clientId;
        Client client;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String caseString = caseReader.readLine();
            switch (caseString) {

                case "getClient":
                    System.out.println("Please enter Integer client's id");
                    try {
                        clientId = Integer.parseInt(reader.readLine());
                        System.out.println(Database.listOfClients.get(clientId));
                    } catch (NumberFormatException e) {
                        System.out.println("You entered String, please enter Integer");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Client with this id does not exist. Enter \"getAllClients\" to see all clients");
                    }
                    break;

                case "getAllClients":
                    for (int i = 0; i < Database.listOfClients.size(); i++) {
                        System.out.println(Database.listOfClients.get(i));
                    }
                    break;

                case "getAccount":
                    try {
                        System.out.println("Please enter account id(in digits)");
                        accountId = Integer.parseInt(reader.readLine());
                        System.out.println(Database.listOfAccounts.get(accountId));
                    } catch (NumberFormatException e) {
                        System.out.println("You entered String, please enter Integer");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Account with this id does not exist. Enter \"getAllAccounts\" to see all account");
                    }
                    break;

                case "getAllAccounts":
                    for (int i = 0; i < Database.listOfAccounts.size(); i++) {
                        System.out.println(Database.listOfAccounts.get(i));
                    }
                    break;

                case "createClient":
                    System.out.println("To create a client, use one of the two templates");
                    System.out.println("Template for Individual:");
                    System.out.println("Name Surname age(in digits)");
                    System.out.println("Template for Legal Entity:");
                    System.out.println("TypeOfBusiness ResponsiblePersonName");
                    userInput = reader.readLine();
                    splitedUserInput = userInput.split(" ");
                    if (!CaseHelper.insertionCheck(splitedUserInput)) break;
                    CaseHelper.createClient(splitedUserInput);
                    break;

                case "createAccount":
                    System.out.println("To create an account, use one of the two templates");
                    System.out.println("Template for Deposit:");
                    System.out.println("clientId(int) InitialContribution(int) term(int) persentage(double) currency(USD or EUR or RUB)");
                    System.out.println("Template for Account:");
                    System.out.println("clientId(int) InitialContribution(int)");
                    userInput = reader.readLine();
                    splitedUserInput = userInput.split(" ");
                    CaseHelper.createAccount(splitedUserInput);
                    break;

                case "deleteAccount":
                    System.out.println("Please enter id of account that you want to delete");
                    try {
                        accountId = Integer.parseInt(reader.readLine());
                        new Manager().deleteAccount(accountId);
                        System.out.println("Account with id: " + accountId + " successfully deleted");
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("This client Id does not exist");
                    }catch (NumberFormatException n){
                        System.out.println("You entered String, please enter Integer");
                    }
                    break;

                case "setTerm":
                    System.out.println("Please enter id of deposit, which term you want to set and term");
                    System.out.println("Template:");
                    System.out.println("depositId term");
                    userInput = reader.readLine();
                    splitedUserInput = userInput.split(" ");
                    CaseHelper.setTerm(splitedUserInput);
                    break;

                case "takeMoney":
                    System.out.println("Please enter id of account, from which you want to take money and amount of money that you want to take");
                    System.out.println("Template:");
                    System.out.println("accountId money(int)");
                    userInput = reader.readLine();
                    splitedUserInput = userInput.split(" ");
                    CaseHelper.takeMoney(splitedUserInput);
                    break;

                case "putMoney":
                    System.out.println("Please enter id of account, into which you want to put money and amount of money that you want to put");
                    System.out.println("Template:");
                    System.out.println("accountId money(int)");
                    userInput = reader.readLine();
                    splitedUserInput = userInput.split(" ");
                    CaseHelper.putMoney(splitedUserInput);
                    break;

                case "accountBalanceCheck":
                    System.out.println("Please enter id of account which balance you want to check");
                    try {
                        accountId = Integer.parseInt(reader.readLine());
                        System.out.println("Balance - " + Account.getAccountById(accountId).getAmountOfMoney());
                    }catch (NumberFormatException n){
                        System.out.println("You entered String, please enter Integer");
                    }catch (IndexOutOfBoundsException f){
                        System.out.println("This account id does not exist. Please enter valid id");
                    }
                    break;

                case "allAccountsBalanceCheck":
                    System.out.println("Please enter id of client. And you will see balance of all his accounts");
                    try {
                        clientId = Integer.parseInt(reader.readLine());
                        client = Client.getClientById(clientId);
                        System.out.println(client.allAccountsBalanceCheck());
                    }catch (NumberFormatException e){
                        System.out.println("You entered String, please enter Integer");
                    }catch(IndexOutOfBoundsException i){
                        System.out.println("This client Id does not exist");
                    }
                    break;

                case "getAllClientAccounts":
                    System.out.println("Please enter id of client which account you want to get");
                    try {
                        clientId = Integer.parseInt(reader.readLine());
                        List<Integer> listOfAccounts = Client.getAccountList(clientId);
                        for (Integer listOfAccount : listOfAccounts) {
                            System.out.println(Account.getAccountById(listOfAccount));
                        }
                    }catch (NumberFormatException n){
                        System.out.println("You entered String, please enter Integer");
                    }catch (NullPointerException n){
                        System.out.println("This client Id does not exist");
                    }

                    break;

                case "sortAccount":
                    System.out.println("Please enter id of client which accounts you want to sort");
                    try {
                        client = Client.getClientById(Integer.parseInt(reader.readLine()));
                        TreeSet<Account> sortedAccounts = client.sortAccounts();
                        System.out.println("Sorted account:");
                        for (Account account : sortedAccounts) {
                            System.out.println(account);
                        }
                    }catch (NumberFormatException n){
                        System.out.println("You entered String, please enter Integer");
                    }
                    break;

                case "help":
                    CaseHelper.help();
                    break;

                case "exit":
                    System.out.println("Do you want to serialize your progress? Print \"Y\" or \"N\"");
                    String answer = reader.readLine();
                    if (answer.equals("Y")) {
                        Serialization.clientSerialization(Database.listOfClients);
                        Serialization.accountSerialization(SerializationHelper.getAccountList());
                        System.out.println("Serialization successfully completed");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignored) {}
                    }else System.out.println("Serialization ignored");
                    System.out.println("Do you really want to exit? Print \"Y\" or \"N\"");
                    answer = reader.readLine();
                    if (answer.equals("Y")) return;
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }

        workInApp(caseReader);
        }
    }

}

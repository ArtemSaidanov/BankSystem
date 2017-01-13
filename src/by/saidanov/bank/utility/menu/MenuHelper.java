package by.saidanov.bank.utility.menu;

import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.Manager;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.client.LegalEntity;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.exceptions.NotEnoughMoneyException;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;
import by.saidanov.bank.utility.factory.ClientFactory;
import by.saidanov.bank.utility.serialization.Serialization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Godfrid on 13.01.2017.
 */
class MenuHelper {

    static void workInApp(BufferedReader caseReader) throws IOException {
        System.out.println("You are in main menu. Please enter a base command or enter \"help\" to see all base commands.");
        int accountId;
        String userInput;
        String[] splitedUserInput;
        int clientId;
        Client client;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String caseString = caseReader.readLine();
        switch (caseString){

            case "getClient":
                System.out.println("Please enter Integer client's id");
                try {
                    clientId = Integer.parseInt(reader.readLine());
                    System.out.println(Database.listOfClients.get(clientId));
                }catch (NumberFormatException e){
                    System.out.println("You entered String, please enter Integer");
                }catch (ArrayIndexOutOfBoundsException e){
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
                }catch (NumberFormatException e){
                    System.out.println("You entered String, please enter Integer");
                }catch (ArrayIndexOutOfBoundsException e){
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
                if (splitedUserInput.length != 2 && splitedUserInput.length != 3){
                    System.out.println("You entered invalid parameters");
                    break;
                }
                if (splitedUserInput.length == 2){
                    client = ClientFactory.createClient(splitedUserInput[0], splitedUserInput[1], new GregorianCalendar());
                    System.out.println("New Legal Entity created");
                    System.out.println(client);
                }else if (splitedUserInput.length == 3){
                    try {
                        client = ClientFactory.createClient(splitedUserInput[0], splitedUserInput[1], Integer.parseInt(splitedUserInput[2]), new GregorianCalendar());
                        System.out.println("New Individual created");
                        System.out.println(client);
                    }catch (NumberFormatException e){
                        System.out.println("Age is invalid");
                    }
                }
                break;

            case "createAccount":
                System.out.println("To create an account, use one of the two templates");
                System.out.println("Template for Deposit:");
                System.out.println("clientId(int) InitialContribution(int) term(int) persentage(double) currency(USD or EUR or RUB)");
                System.out.println("Template for Account:");
                System.out.println("clientId(int) InitialContribution(int)");
                userInput = reader.readLine();
                splitedUserInput = userInput.split(" ");
                try {
                if (Integer.parseInt(splitedUserInput[0]) > Database.listOfClients.size() || Integer.parseInt(splitedUserInput[0]) < 0){
                    System.out.println("This client Id does not exist");
                    break;
                }
                if (splitedUserInput.length != 2 && splitedUserInput.length != 5){
                    System.out.println("You entered invalid parameters");
                    break;
                }
                if (splitedUserInput.length == 2){
                    Account account = new Manager().createAccount(Integer.parseInt(splitedUserInput[0]), Integer.parseInt(splitedUserInput[1]));
                    System.out.println("New Account created");
                    System.out.println(account);
                }else if (splitedUserInput.length == 5){
                    Account account = null;
                    if (splitedUserInput[4].equals("USD")){
                       account = new Manager().createAccount(Integer.parseInt(splitedUserInput[0]), Integer.parseInt(splitedUserInput[1]),
                               Integer.parseInt(splitedUserInput[2]), Double.parseDouble(splitedUserInput[3]), DepositCurrency.USD);
                    }else if (splitedUserInput[4].equals("EUR")){
                        account = new Manager().createAccount(Integer.parseInt(splitedUserInput[0]), Integer.parseInt(splitedUserInput[1]),
                                Integer.parseInt(splitedUserInput[2]), Double.parseDouble(splitedUserInput[3]), DepositCurrency.EUR);
                    }else if (splitedUserInput[4].equals("RUB")){
                        account = new Manager().createAccount(Integer.parseInt(splitedUserInput[0]), Integer.parseInt(splitedUserInput[1]),
                                Integer.parseInt(splitedUserInput[2]), Double.parseDouble(splitedUserInput[3]), DepositCurrency.RUB);
                    }
                    System.out.println("New Deposit created");
                    System.out.println(account);
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid argument. NumberFormatException.");
                }
                break;

            case "deleteAccount":
                System.out.println("Please enter id of account that you want to delete");
                accountId = Integer.parseInt(reader.readLine());
                new Manager().deleteAccount(accountId);
                System.out.println("Account with id: " + accountId + " successfully deleted");
                break;

            case "setTerm":
                System.out.println("Please enter id of deposit, which term you want to set and term");
                System.out.println("Template:");
                System.out.println("depositId term");
                userInput = reader.readLine();
                splitedUserInput = userInput.split(" ");
                try {
                accountId = Integer.parseInt(splitedUserInput[0]);
                int term = Integer.parseInt(splitedUserInput[1]);
                    new Manager().setTerm(Account.getAccountById(accountId), term);
                    System.out.println("Term was changed.");
                } catch (TermCanNotRaiseException ignored) {
                }catch (NumberFormatException e){
                    System.out.println("Invalid argument. NumberFormatException.");
                }
                break;

            case "takeMoney":
                System.out.println("Please enter id of account, from which you want to take money and amount of money that you want to take");
                System.out.println("Template:");
                System.out.println("accountId money(int)");
                userInput = reader.readLine();
                splitedUserInput = userInput.split(" ");
                try {
                    accountId = Integer.parseInt(splitedUserInput[0]);
                    int money = Integer.parseInt(splitedUserInput[1]);
                    new Manager().takeMoney(accountId, money);
                    System.out.println("You have successfully took the money from the account");
                    }catch (NotEnoughMoneyException ignored) {
                    }catch (NumberFormatException e){
                        System.out.println("Invalid argument. NumberFormatException.");
                    }
                break;

            case "putMoney":
                System.out.println("Please enter id of account, into which you want to put money and amount of money that you want to put");
                System.out.println("Template:");
                System.out.println("accountId money(int)");
                userInput = reader.readLine();
                splitedUserInput = userInput.split(" ");
                try {
                    accountId = Integer.parseInt(splitedUserInput[0]);
                    int money = Integer.parseInt(splitedUserInput[1]);
                    new Manager().putMoney(accountId, money);
                    System.out.println("You have successfully put money into the account");
                }catch (NumberFormatException e){
                    System.out.println("Invalid argument. NumberFormatException.");
                }
                break;

            case "accountBalanceCheck":
                System.out.println("Please enter id of account which balance you want to check");
                accountId = Integer.parseInt(reader.readLine());
                System.out.println("Balance - " + Account.getAccountById(accountId).getAmountOfMoney());
                break;

            case "allAccountsBalanceCheck":
                System.out.println("Please enter id of client. And you will see balance of all his accounts");
                clientId = Integer.parseInt(reader.readLine());
                client = Client.getClientById(clientId);
                System.out.println(client.allAccountsBalanceCheck());
                break;

            case "getAllClientAccounts":
                System.out.println("Please enter id of client which account you want to get");
                clientId = Integer.parseInt(reader.readLine());
                client = Client.getClientById(clientId);
                List<Integer> listOfAccounts = client.getAccountList(clientId);
                for (int i = 0; i < listOfAccounts.size(); i++) {
                    System.out.println(Account.getAccountById(listOfAccounts.get(i)));
                }
                break;

            case "help":
                help();
                break;

            case "exit":
                System.out.println("Do you want to serialize your progress? Print \"Y\" or \"N\"");
                String answer = reader.readLine();
                if (answer.equals("Y")){
                    Serialization.clientSerialization(Database.listOfClients);
                    Serialization.accountSerialization(Database.listOfAccounts);
                    System.out.println("Serialization successfully completed");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
                return;

            default:
                System.out.println("Invalid command.");
                break;
        }
        workInApp(caseReader);
    }

    private static void help() {
        System.out.println("List of base commands:");
        System.out.println("\"createClient\": \n create new client");
        System.out.println("\"getClient\": \n return you a client");
        System.out.println("\"getAllClients\": \n return you all clients");
        System.out.println("\"createAccount\": \n create new account");
        System.out.println("\"getAccount\": \n return you an account");
        System.out.println("\"getAllAccounts\": \n return you all accounts");
        System.out.println("\"deleteAccount\": \n delete an account");
        System.out.println("\"putMoney\": \n put money into the account");
        System.out.println("\"takeMoney\": \n take money from the account");
        System.out.println("\"setTerm\": \n set deposit term");
        System.out.println("\"accountBalanceCheck\": \n return you account's balance");
        System.out.println("\"allAccountsBalanceCheck\": \n return you amount of money that Client has on all his accounts");
        System.out.println("\"getAllClientAccounts\": \n return you all Client's accounts");
        System.out.println("\"exit\": \n close application");
    }
}

package by.saidanov.bank.utility.menu;

import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.beans.Manager;
import by.saidanov.bank.beans.account.Account;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.exceptions.NotEnoughMoneyException;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;
import by.saidanov.bank.utility.Constants;
import by.saidanov.bank.utility.factory.ClientFactory;

import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * CaseHelper
 *
 * @version 1.0
 *
 * Date 15.01.2017
 *
 * Methods from this class unload cases from switch statement in {@link MenuHelper}
 */
final class CaseHelper {

    static void setTerm(String[] splitedUserInput) throws IOException {
        int accountId;
        try {
            accountId = Integer.parseInt(splitedUserInput[Constants.INPUTED_ACCOUNT_ID]);
            int term = Integer.parseInt(splitedUserInput[Constants.INPUTED_SET_TERM]);
            new Manager().setTerm(Account.getAccountById(accountId), term);
            System.out.println("Term was changed.");
        } catch (TermCanNotRaiseException ignored) {

        } catch (NumberFormatException e) {
            System.out.println("Invalid argument. NumberFormatException.");
        } catch (ClassCastException c){
            System.out.println("This account is not a deposit.");
        } catch (ArrayIndexOutOfBoundsException a){
            System.out.println("You must enter 2 arguments: depositId and term, if you want to change term.");
        }
    }

    static void putMoney(String[] splitedUserInput) {
        int accountId;
        try {
            accountId = Integer.parseInt(splitedUserInput[Constants.ACCOUNT_IN_FILE_ID]);
            int money = Integer.parseInt(splitedUserInput[Constants.INPUTED_MONEY]);
            new Manager().putMoney(accountId, money);
            System.out.println("You have successfully put money into the account");
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument. NumberFormatException.");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("You must enter 2 arguments: accountID and money that you want to put on the account.");
        }
    }

    static void takeMoney(String[] splitedUserInput) {
        int accountId;
        try {
            accountId = Integer.parseInt(splitedUserInput[Constants.INPUTED_ACCOUNT_ID]);
            int money = Integer.parseInt(splitedUserInput[Constants.INPUTED_MONEY]);
            new Manager().takeMoney(accountId, money);
            System.out.println("You have successfully took the money from the account");
            } catch (NotEnoughMoneyException ignored) {
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument. NumberFormatException.");
            } catch (ArrayIndexOutOfBoundsException a){
                System.out.println("You must enter 2 arguments: accountID and money that you want to take from account.");
        }
    }

    static void createClient(String[] splitedUserInput) {
        Client client;
        try {
        if (splitedUserInput.length != Constants.LEGAL_ENTITY_LENGTH && splitedUserInput.length != Constants.INDIVIDUAL_LENGTH) {
            System.out.println("You entered invalid parameters");
            return;
        }
        if (splitedUserInput.length == Constants.LEGAL_ENTITY_LENGTH) {
            client = ClientFactory.createClient(splitedUserInput[Constants.INPUTED_TYPE_OF_BUSINESS],
                    splitedUserInput[Constants.INPUTED_RESPONSINLE_PERSON_NAME], new GregorianCalendar());
            System.out.println("New Legal Entity created");
            System.out.println(client);
        } else if (splitedUserInput.length == Constants.INDIVIDUAL_LENGTH) {
                client = ClientFactory.createClient(splitedUserInput[Constants.INPUTED_NAME],
                        splitedUserInput[Constants.INPUTED_SURNAME],
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_AGE]), new GregorianCalendar());
                System.out.println("New Individual created");
                System.out.println(client);
            }
        } catch (NumberFormatException e) {
                System.out.println("Age is invalid");
            }
    }

    static void createAccount(String[] splitedUserInput) {
        try {
            if (Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]) > Database.listOfClients.size() || Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]) < 0) {
                System.out.println("This client Id does not exist");
                return;
            }
            if (splitedUserInput.length != Constants.ACCOUNT_LENGTH && splitedUserInput.length != Constants.DEPOSIT_LENGTH) {
                System.out.println("You entered invalid parameters");
                return;
            }
            if (splitedUserInput.length == Constants.ACCOUNT_LENGTH) {
                Account account = new Manager().createAccount(Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_INITIAL_CONTRIBUTION]));
                System.out.println("New Account created");
                System.out.println(account);
            } else if (splitedUserInput.length == Constants.DEPOSIT_LENGTH) {
                try {
                    Account account = getDeposit(splitedUserInput);
                    System.out.println("New Deposit created");
                    System.out.println(account);
                }catch (IllegalArgumentException e){
                    System.out.println("Invalid currency. Currency can be: USD, EUR, RUB");
                }

            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument. NumberFormatException.");
        }
    }

    private static Account getDeposit(String[] splitedUserInput) {
        Account account;
        switch (splitedUserInput[Constants.INPUTED_DEPOSIT_CURRENCY]) {
            case "USD":
                account = new Manager().createAccount(Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_INITIAL_CONTRIBUTION]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_TERM]),
                        Double.parseDouble(splitedUserInput[Constants.INPUTED_PERSENTAGE]),
                        DepositCurrency.USD);

                break;
            case "EUR":
                account = new Manager().createAccount(Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_INITIAL_CONTRIBUTION]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_TERM]),
                        Double.parseDouble(splitedUserInput[Constants.INPUTED_PERSENTAGE]),
                        DepositCurrency.EUR);
                break;
            case "RUB":
                account = new Manager().createAccount(Integer.parseInt(splitedUserInput[Constants.INPUTED_CLIENT_ID]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_INITIAL_CONTRIBUTION]),
                        Integer.parseInt(splitedUserInput[Constants.INPUTED_TERM]),
                        Double.parseDouble(splitedUserInput[Constants.INPUTED_PERSENTAGE]),
                        DepositCurrency.RUB);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return account;
    }

    static void help() {
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
        System.out.println("\"sortAccounts\": \n return you sorted by amount of money list of Client'saccounts");
        System.out.println("\"exit\": \n close application");
    }

    /**
     * Checks inserted arguments when user tried to createAccount or createClient and insures that parameters:
     * <p>- Name</p>
     * <p>- Surname</p>
     * <p>- TypeOfBusiness</p>
     * <p>- ResponsiblePersonName</p>
     * <p>will not be integers</p>
     * @return true if all parameters are correct*/
    static boolean insertionCheck(String[] splitedUserInput) {
        try {
            Integer.parseInt(splitedUserInput[0]);
            Integer.parseInt(splitedUserInput[1]);
            System.out.println("Creation failed. First and second arguments must be Strings. You entered ints." +
                    "Please enter Strings if you want to create new Client");
            return false;
        }catch (NumberFormatException e){

        }catch (IndexOutOfBoundsException e){
            System.out.println("Not enough parameters.");
        }
        return true;
    }
}

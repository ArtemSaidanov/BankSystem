package by.saidanov.bank.utility;

import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.exceptions.WrongAccountIdException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Validator
 *
 * @version 1.0
 *
 * Date 06.01.2017
 *
 * This class validate account ID
 */
public final class Validator {

    /**This method ensures that account ID won't be < 0*/
    public static void accountIDValidation(int accountID) throws WrongAccountIdException {
        if (accountID < 0) {
            throw new WrongAccountIdException("Account ID can't be < 0");
        }
    }

    public static int accountInFileValidation(int accountId) throws IOException {
        int accountInFileID;
        int accountIdCounter = 0;
        boolean isInFile = false;
        try {
            try(BufferedReader fileReader = new BufferedReader(new FileReader(Constants.ACCOUNT_FILE_PATH))) {
            /*This "while" reads lines from file*/
                while (fileReader.ready()) {
                    accountIdCounter++;
                    String lineFromFile = fileReader.readLine();
                    /*Split lineFromFile by spaces*/
                    String[] splitLineFromFile = lineFromFile.split(" ");
                    try {
                        accountInFileID = Integer.parseInt(splitLineFromFile[Constants.ACCOUNT_IN_FILE_ID]);
                    } catch (NumberFormatException e) {
                        System.out.println("NumberFormatException");
                        continue;
                    }
                    if (accountInFileID == accountId) {
                        isInFile = true;
                    }
                }
                if (isInFile){
                    accountId = accountIdCounter;
                }
            }
        }catch (FileNotFoundException f){
        }
        return accountId;
    }

    /**
     * This method prevents rewrite
     * @param client - that we want to add into the file
     */
    public static boolean rewriteVerificationClientID(Client client) throws IOException {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.CLIENT_FILE_PATH))) {
            int clientInFileID;
            while (fileReader.ready()) {
                String lineFromFile = fileReader.readLine();
                /*Split lineFromFile by spaces*/
                String[] splitLineFromFile = lineFromFile.split(" ");
                /*Parse client id from file*/
                try {
                    clientInFileID = Integer.parseInt(splitLineFromFile[Constants.CLIENT_IN_FILE_ID]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException in ClientIO");
                    continue;
                }
                if (clientInFileID == client.getClientId()) {
                    return true;
                }
            }
            return false;
        }
    }
}

package by.saidanov.bank.utility.io;


import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.beans.client.Client;
import by.saidanov.bank.beans.client.Individual;
import by.saidanov.bank.beans.client.LegalEntity;
import by.saidanov.bank.utility.Constants;
import by.saidanov.bank.utility.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClientIO
 *
 * @version 1.0
 *
 * Date 06.08.2017
 *
 * This class works with files like with Database
 * Client includes list of clients with all client's parameters
 */
public final class ClientIO {

    /**
     * This method adds new Client to File
     * @param client - this is the client that we add to file
     */
    public void addClientToFile(Client client) throws IOException {

        try (FileWriter fileWriter = new FileWriter(Constants.CLIENT_FILE_PATH, true)) {
            if (Validator.rewriteVerificationClientID(client)) return;
            List<Integer> listOfAccounts = Database.clientsAndAccounts.get(client.getClientId());
            String stringOfAccounts = "";
            //Add all client's accounts to string
            if (listOfAccounts != null) {
                for (Integer accountId : listOfAccounts) {
                    stringOfAccounts += listOfAccounts.get(accountId);
                    stringOfAccounts += ",";
                }
            }
            if (client instanceof Individual) {
                fileWriter.write(client.getClientId() + " - Client ID"
                        + "; Name: " + ((Individual) client).getName()
                        + "; Surname: " + ((Individual) client).getSurname()
                        + "; Age: " + ((Individual) client).getAge()
                        + "; List of Accounts: " + stringOfAccounts
                        + "\r" + "\n");
            } else if (client instanceof LegalEntity) {
                fileWriter.write(client.getClientId() + " - Client ID"
                        + "; Type of Business: " + ((LegalEntity) client).getTypeOfBusiness()
                        + "; Responsible person: " + ((LegalEntity) client).getResponsiblePersonName()
                        + "; List of Accounts: " + stringOfAccounts
                        + "\r" + "\n");
            }
        }
    }

    /**
     * This method adds account to List of Account in ClientIO
     * @param clientID - id of client which account we add
     * @param accountID - id of account that we add
     */
    public void addAccountToClientFile(int clientID, int accountID) throws IOException {
        int clientInFileID;
        List<String> listOfLines;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.CLIENT_FILE_PATH))) {
            listOfLines = new ArrayList<>();
        /*This "while" reads lines from file*/
            while (fileReader.ready()) {
                String lineFromFile = fileReader.readLine();
                /*Split lineFromFile by spaces*/
                String[] splitLineFromFile = lineFromFile.split(" ");
                /*Convert splitted line massive into List*/
                List<String> splitLineList = new ArrayList<>();
                Collections.addAll(splitLineList, splitLineFromFile);
                /*Parse client id from file*/
                try {
                    clientInFileID = Integer.parseInt(splitLineFromFile[Constants.CLIENT_IN_FILE_ID]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException in ClientIO");
                    continue;
                }
                /*if client id from file == client id from method argument*/
                if (clientInFileID == clientID) {
                    /*add new client's account id to file*/
                    splitLineList.add(String.valueOf(accountID));
                }
                lineFromFile = "";
                /*Cycle to fill our string with String[] splitLineFromFile*/
                for (int j = 0; j < splitLineList.size(); j++) {
                    lineFromFile += splitLineList.get(j) + " ";
                }
                listOfLines.add(lineFromFile);
            }
        }
        /*Clean file*/
        FileCleaner.cleanFile(Constants.CLIENT_FILE_PATH);
        /*Writing in file line by line*/
        try (FileWriter writer = new FileWriter(Constants.CLIENT_FILE_PATH, true)) {
            for (String s : listOfLines) {
                writer.write(s + "\r" + "\n");
            }
        }
    }

    /**
     * When client deletes his account this method removes accountId from ClientIO
     * @param clientID - id of client which account we add
     * @param accountID - id of account that we add
     */
    public void deleteAccountFromClientFile(int clientID, int accountID) throws IOException{
        final int accountPositionInList = 13;
        int clientInFileID;
        List<String> listOfLines;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(Constants.CLIENT_FILE_PATH))) {
            listOfLines = new ArrayList<>();

        /*This "while" reads lines from file*/
            while (fileReader.ready()) {
                String lineFromFile = fileReader.readLine();
                /*Split lineFromFile by spaces*/
                String[] splitLineFromFile = lineFromFile.split(" ");
                /*Convert splitted line massive into List*/
                List<String> splitLineList = new ArrayList<>();
                for (int i = 0; i < splitLineFromFile.length; i++) {
                    splitLineList.add(splitLineFromFile[i]);
                }
                /*Parse client id from file*/
                try {
                    clientInFileID = Integer.parseInt(splitLineFromFile[Constants.CLIENT_IN_FILE_ID]);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException in ClientIO");
                    continue;
                }
                /*if client id from file == client id from method argument*/
                if (clientInFileID == clientID) {
                    int accountInFileID;
                    for (int i = accountPositionInList; i < splitLineList.size(); i++) {
                        accountInFileID = Integer.parseInt(splitLineList.get(i));
                        if (accountID == accountInFileID) {
                            splitLineList.remove(i);
                        }
                    }
                }
                lineFromFile = "";
                /*Cycle to fill our string with String[] splitLineFromFile*/
                for (int j = 0; j < splitLineList.size(); j++) {
                    lineFromFile += splitLineList.get(j) + " ";
                }
                listOfLines.add(lineFromFile);
            }
        }
        /*Clean file*/
        FileCleaner.cleanFile(Constants.CLIENT_FILE_PATH);
        /*Writing in file line by line*/
        try (FileWriter writer = new FileWriter(Constants.CLIENT_FILE_PATH, true)) {
            for (String s : listOfLines) {
                writer.write(s + "\r" + "\n");
            }
        }
    }

}

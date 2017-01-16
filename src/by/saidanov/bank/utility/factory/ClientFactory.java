package by.saidanov.bank.utility.factory;

import by.saidanov.bank.beans.database.Database;
import by.saidanov.bank.utility.io.ClientIO;
import by.saidanov.bank.beans.client.Individual;
import by.saidanov.bank.beans.client.LegalEntity;
import by.saidanov.bank.beans.client.Client;

import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * ClientFactory
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>This class creates Clients</p>
 */
public final class ClientFactory {

    /**
     * This method creates Individual
     * @param name name of Individual
     * @param surname surname of Individual
     * @param age age of Individual
     * @param gregorianCalendar used for obtaining dateOfRegistration
     */
    public static Client createClient(String name, String surname, int age, GregorianCalendar gregorianCalendar) {
        int clientId = getClientId();
        Client client = new Individual(clientId, name, surname, age, gregorianCalendar);
        addToFile(client);
        Database.listOfClients.add(client);
        return client;
    }

    /**
     * This method creates LegalEntity
     * @param typeOfBusiness the type of business activity
     * @param responsiblePersonName person communicating with the manager
     * @param gregorianCalendar used for obtaining dateOfRegistration
     */
    public static Client createClient(String typeOfBusiness, String responsiblePersonName, GregorianCalendar gregorianCalendar){
        int clientId = getClientId();
        Client client = new LegalEntity(clientId, typeOfBusiness, responsiblePersonName, gregorianCalendar);
        addToFile(client);
        Database.listOfClients.add(client);
        return client;
    }

    private static int getClientId() {
        int clientId = Client.clientIdCounter++;
        if (Database.listOfClients.size() != 0){
            clientId = Database.listOfClients.size();
        }
        return clientId;
    }

    /**
     * This method adds client to client file
     * @param client this client will be added to file
     */
    private static void addToFile(Client client) {
        try {
            ClientIO clientIO = new ClientIO();
            clientIO.addClientToFile(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

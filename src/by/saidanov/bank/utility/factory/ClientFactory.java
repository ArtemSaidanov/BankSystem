package by.saidanov.bank.utility.factory;

import by.saidanov.bank.utility.database.Database;
import by.saidanov.bank.utility.io.ClientIO;
import by.saidanov.bank.beans.client.Individual;
import by.saidanov.bank.beans.client.LegalEntity;
import by.saidanov.bank.beans.client.Client;

import java.io.IOException;

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
     */
    public static Client createClient(String name, String surname, int age) {
        int clientId = Client.clientIdCounter++;
        Client client = new Individual(clientId, name, surname, age);
        try {
            ClientIO clientIO = new ClientIO();
            clientIO.addClientToFile(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database.listOfClients.add(client);
        return client;
    }

    /**
     * This method creates LegalEntity
     */
    public static Client createClient(String typeOfBusiness, String bossName){
        int clientId = Client.clientIdCounter++;
        Client client = new LegalEntity(clientId, typeOfBusiness, bossName);
        try {
            ClientIO clientIO = new ClientIO();
            clientIO.addClientToFile(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database.listOfClients.add(client);
        return client;
    }
}

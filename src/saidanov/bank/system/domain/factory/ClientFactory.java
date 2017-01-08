package saidanov.bank.system.domain.factory;

import saidanov.bank.system.beans.fileworking.ClientFile;
import saidanov.bank.system.domain.client.Individual;
import saidanov.bank.system.domain.client.LegalEntity;
import saidanov.bank.system.domain.client.Client;
import saidanov.bank.system.beans.database.Database;

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
            ClientFile clientFile = new ClientFile();
            clientFile.addClientToFile(client);
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
            ClientFile clientFile = new ClientFile();
            clientFile.addClientToFile(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Database.listOfClients.add(client);
        return client;
    }
}

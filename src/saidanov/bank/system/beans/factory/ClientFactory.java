package saidanov.bank.system.beans.factory;

import saidanov.bank.system.beans.client.Businessman;
import saidanov.bank.system.beans.client.Client;
import saidanov.bank.system.beans.client.Person;
import saidanov.bank.system.beans.database.Database;

/**
 * This class creates Clients
 */
public final class ClientFactory {
    public static Client createClient(String name, String surname, int age) {
        int clientId = Client.clientIdCounter++;
        Client client = new Person(clientId, name, surname, age);
        Database.listOfClients.add(client);
        return client;
    }
    public static Client createClient(String typeOfBusiness, String bossName){
        int clientId = Client.clientIdCounter++;
        Client client = new Businessman(clientId, typeOfBusiness, bossName);
        Database.listOfClients.add(client);
        return client;
    }
}

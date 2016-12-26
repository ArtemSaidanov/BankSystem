package saidanov.bank.system.beans.factory;

import saidanov.bank.system.beans.client.Businessman;
import saidanov.bank.system.beans.client.Client;
import saidanov.bank.system.beans.client.Person;
import saidanov.bank.system.beans.database.Database;

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
     * This method creates Person*/
    public static Client createClient(String name, String surname, int age) {
        int clientId = Client.clientIdCounter++;
        Client client = new Person(clientId, name, surname, age);
        Database.listOfClients.add(client);
        return client;
    }

    /**
     * This method creates Businessman*/
    public static Client createClient(String typeOfBusiness, String bossName){
        int clientId = Client.clientIdCounter++;
        Client client = new Businessman(clientId, typeOfBusiness, bossName);
        Database.listOfClients.add(client);
        return client;
    }
}

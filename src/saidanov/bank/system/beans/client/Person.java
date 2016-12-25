package saidanov.bank.system.beans.client;


/**
 * <p>Person class describing a separate man</p>
 */
public class Person extends Client {

    private String name;
    private String surname;
    private int age;
    /**This field was added only to demonstrate the power of polymorphism*/
    private int happiness;

    public int getHappiness() {
        return happiness;
    }
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    @Override
    public String toString() {
        return "clientId " + this.getClientId() + "; Person -" + " Name: " + name + "; Surname: " + surname + "; Age: " + age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * <p>Constructor of Person class</p>
     * @param clientId unique clients id
     * @param name name of Person
     * @param surname surname of Person
     * @param age age of Person
     */
    public Person(int clientId, String name, String surname, int age) {
        super(clientId);
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    @Override
    public void takeMoneyFromAccount(int accountId, int money) {
        super.takeMoneyFromAccount(accountId, money);
        System.out.println("person Class");
        happiness++;
    }

}

package saidanov.bank.system.beans.client;


/**
 * Person
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>Person class describing a separate man</p>
 */
public class Person extends Client {

    private String name;
    private String surname;
    private int age;
    /**This field was added only to demonstrate the power of polymorphism*/
    private int happiness;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (happiness != person.happiness) return false;
        if (name != null ? !name.equals(person.name) : person.name != null)
            return false;
        return surname != null ? surname.equals(person.surname)
                : person.surname == null;

    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + happiness;
        return result;
    }
    @Override
    public String toString() {
        return "clientId " + this.getClientId() + "; Person -" +
                " Name: " + name + "; Surname: " + surname + "; Age: " + age;
    }

    @Override
    public void takeMoneyFromAccount(int accountId, int money) {
        super.takeMoneyFromAccount(accountId, money);
        System.out.println("person Class");
        happiness++;
    }

    public int getHappiness() {
        return happiness;
    }
    public void setHappiness(int happiness) {
        this.happiness = happiness;
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
}

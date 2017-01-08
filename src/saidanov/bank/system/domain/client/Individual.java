package saidanov.bank.system.domain.client;


import saidanov.bank.system.exceptions.NotEnoughMoneyException;

/**
 * Individual
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>Individual class describing a separate man</p>
 */
public class Individual extends Client {

    private String name;
    private String surname;
    private int age;
    private int happiness;

    /**
     * <p>Constructor of Individual class</p>
     * @param clientId unique clients id
     * @param name name of Individual
     * @param surname surname of Individual
     * @param age age of Individual
     */
    public Individual(int clientId, String name, String surname, int age) {
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

        Individual individual = (Individual) o;

        if (age != individual.age) return false;
        if (happiness != individual.happiness) return false;
        if (name != null ? !name.equals(individual.name) : individual.name != null)
            return false;
        return surname != null ? surname.equals(individual.surname)
                : individual.surname == null;

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
        return "clientId " + this.getClientId() + "; Individual -" +
                " Name: " + name + "; Surname: " + surname + "; Age: " + age;
    }

    @Override
    public void takeMoney(int accountId, int money) throws NotEnoughMoneyException {
        super.takeMoney(accountId, money);
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

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

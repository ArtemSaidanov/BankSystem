package saidanov.bank.system.beans.client;

/**
 * Businessman
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>Businessman class describing a business</p>
 */
public class Businessman extends Client {

    private String typeOfBusiness;

    private String bossName;

    /**This field was added only to demonstrate the power of polymorphism*/
    private int totalInvestment;

    /**
     * <p>Constructor of Businessman class</p>
     * @param clientId unique id of Client
     * @param typeOfBusiness the type of business activity
     * @param bossName person communicating with the manager
     */
    public Businessman(int clientId, String typeOfBusiness, String bossName) {
        super(clientId);
        this.typeOfBusiness = typeOfBusiness;
        this.bossName = bossName;
    }

    @Override
    public String toString() {
        return "clientId " + this.getClientId() + "; Businessman -" +
                " TypeOfBusiness: " + typeOfBusiness + "; BossName: " + bossName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Businessman that = (Businessman) o;

        if (totalInvestment != that.totalInvestment) return false;
        if (typeOfBusiness != null ? !typeOfBusiness.equals(that.typeOfBusiness) : that.typeOfBusiness != null)
            return false;
        return bossName != null ? bossName.equals(that.bossName) : that.bossName == null;

    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (typeOfBusiness != null ? typeOfBusiness.hashCode() : 0);
        result = 31 * result + (bossName != null ? bossName.hashCode() : 0);
        result = 31 * result + totalInvestment;
        return result;
    }

    @Override
    public void takeMoneyFromAccount(int accountId, int money) {
        super.takeMoneyFromAccount(accountId, money);
        System.out.println("buisness Class");
        totalInvestment += money;
    }

    public int getTotalInvestment() {
        return totalInvestment;
    }
    public void setTotalInvestment(int totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }
    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getBossName() {
        return bossName;
    }
    public void setBossName(String bossName) {
        this.bossName = bossName;
    }
}

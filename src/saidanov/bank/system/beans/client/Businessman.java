package saidanov.bank.system.beans.client;

/**
 * <p>Businessman class describing a business</p>
 */
public class Businessman extends Client {

    private String typeOfBusiness;
    private String bossName;
    /**This field was added only to demonstrate the power of polymorphism*/
    private int totalInvestment;


    public int getTotalInvestment() {
        return totalInvestment;
    }
    public void setTotalInvestment(int totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    @Override
    public String toString() {
        return "clientId " + this.getClientId() + "; Businessman -" + " TypeOfBusiness: " + typeOfBusiness + "; BossName: " + bossName;
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
    public void takeMoneyFromAccount(int accountId, int money) {
        super.takeMoneyFromAccount(accountId, money);
        System.out.println("buisness Class");
        totalInvestment += money;
    }

}

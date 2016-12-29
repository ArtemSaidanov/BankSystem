package saidanov.bank.system.domain.client;

/**
 * LegalEntity
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * <p>LegalEntity class describing a business</p>
 */
public class LegalEntity extends Client {

    private String typeOfBusiness;

    private String responsiblePersonName;

    /**This field was added only to demonstrate the power of polymorphism*/
    private int totalInvestment;

    /**
     * <p>Constructor of LegalEntity class</p>
     * @param clientId unique id of Client
     * @param typeOfBusiness the type of business activity
     * @param responsiblePersonName person communicating with the manager
     */
    public LegalEntity(int clientId, String typeOfBusiness, String responsiblePersonName) {
        super(clientId);
        this.typeOfBusiness = typeOfBusiness;
        this.responsiblePersonName = responsiblePersonName;
    }

    @Override
    public String toString() {
        return "clientId " + this.getClientId() + "; LegalEntity -" +
                " TypeOfBusiness: " + typeOfBusiness + "; BossName: " + responsiblePersonName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LegalEntity that = (LegalEntity) o;

        if (totalInvestment != that.totalInvestment) return false;
        if (typeOfBusiness != null ? !typeOfBusiness.equals(that.typeOfBusiness) : that.typeOfBusiness != null)
            return false;
        return responsiblePersonName != null ? responsiblePersonName.equals(that.responsiblePersonName) : that.responsiblePersonName == null;

    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (typeOfBusiness != null ? typeOfBusiness.hashCode() : 0);
        result = 31 * result + (responsiblePersonName != null ? responsiblePersonName.hashCode() : 0);
        result = 31 * result + totalInvestment;
        return result;
    }

    @Override
    public void takeMoney(int accountId, int money) {
        super.takeMoney(accountId, money);
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

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }
    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }
}

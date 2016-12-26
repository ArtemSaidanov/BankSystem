package saidanov.bank.system.beans.account;

import saidanov.bank.system.beans.tools.DepositCurrency;

/**
 * Deposit
 *
 * @version  1.0
 *
 * Date 26.12.2016
 *
 * Deposit is the amount of money that the Client sent to the Bank in order to get income as percentages.
 */
public class Deposit extends Account {

    private int term;
    private double persentage;
    private DepositCurrency currency;
    private int accountId;

    public Deposit(int clientId, int initialContribution, int term, double persentage,
                   DepositCurrency currency, int accountId) {
        super(accountId, clientId, initialContribution);
        this.term = term;
        this.persentage = persentage;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Deposit deposit = (Deposit) o;

        if (term != deposit.term) return false;
        if (Double.compare(deposit.persentage, persentage) != 0) return false;
        if (accountId != deposit.accountId) return false;
        return currency == deposit.currency;

    }
    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + term;
        temp = Double.doubleToLongBits(persentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + accountId;
        return result;
    }

    public int getTerm() {
        return term;
    }
    public void setTerm(int term) {
        this.term = term;
    }

    public double getPersentage() {
        return persentage;
    }
    public void setPersentage(double persentage) {
        this.persentage = persentage;
    }

    public DepositCurrency getCurrency() {
        return currency;
    }
    public void setCurrency(DepositCurrency currency) {
        this.currency = currency;
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}

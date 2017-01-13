package by.saidanov.bank.beans.account;

import by.saidanov.bank.utility.io.AccountIO;
import by.saidanov.bank.beans.DepositCurrency;
import by.saidanov.bank.exceptions.TermCanNotRaiseException;

import java.io.IOException;

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

    /**
     * the initial deposit term*/
    private int term;
    private double persentage;
    private DepositCurrency currency;
    private int depositProfit;

    public Deposit(int clientId, int initialContribution, int term, double persentage,
                   DepositCurrency currency, int accountId) {
        super(clientId, initialContribution, accountId);
        this.amountOfMoney = initialContribution;
        this.term = term;
        this.persentage = persentage;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (! super.equals(o)) return false;

        Deposit deposit = (Deposit) o;

        if (term != deposit.term) return false;
        if (Double.compare(deposit.persentage, persentage) != 0) return false;
        if (depositProfit != deposit.depositProfit) return false;
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
        result = 31 * result + depositProfit;
        return result;
    }

    @Override
    public String toString() {
        return "Deposit : " + accountId
                + "; clientId " + super.getClientId()
                + "; initialContribution : " + super.getInitialContribution()
                + "; term : " + term
                + "; persentage : " + persentage
                + "; currency : " + currency
                + "; depositProfit : " + depositProfit
                + "; amountOfMoney : " + amountOfMoney;
    }

    @Override
    public int getAmountOfMoney() {
        return super.getAmountOfMoney();
    }
    /**
     * This method changes amount of money on Client deposit.
     * <p>And if Client takes money before the term of deposit left,
     * Client stills a half of his profit</p>
     * @param amountOfMoney after performing operations
     * */
    @Override
    public int setAmountOfMoney(int amountOfMoney) {
        int oldBalance = super.getAmountOfMoney();
        /**This "if" works for Client.takeMoney method
         * <p>"else" works for Client.putMoney method</p>*/
        if(oldBalance > amountOfMoney && term != 0){
            depositProfit = depositProfit/2;
            super.setAmountOfMoney(oldBalance - amountOfMoney);
        }else super.setAmountOfMoney(amountOfMoney);
        return amountOfMoney;
    }

    public int getTerm() {
        return term;
    }
    /**
     * @param pastTerm it indicates how many months of the deposit remains
     */
    public void setTerm(int pastTerm) throws TermCanNotRaiseException, IOException {
        if (pastTerm > this.term) throw new TermCanNotRaiseException("You can't raise term of deposit. Term of deposit may only fall.");
        int oldProfit = depositProfit;
        setDepositProfit(this.term - pastTerm, this.persentage);
        setAmountOfMoney(getAmountOfMoney() + (depositProfit - oldProfit));
        this.term = pastTerm;
        new AccountIO().changeTerm(this.getAccountId(), pastTerm, depositProfit);
    }

    public int getDepositProfit() {
        return depositProfit;
    }
    /**
     * This method set profit from deposit for the number of past months
     * @param termLeft it indicates how many months of the deposit left
     */
    public void setDepositProfit(int termLeft, double persentage) {
        int monthProfit = (int) (getInitialContribution()/100 * persentage);
        this.depositProfit = this.depositProfit + (termLeft * monthProfit);
    }

    public double getPersentage() {
        return persentage;
    }

    public DepositCurrency getCurrency() {
        return currency;
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }




}

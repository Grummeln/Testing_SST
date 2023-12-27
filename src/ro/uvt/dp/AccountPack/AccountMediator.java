package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.Account;
import ro.uvt.dp.Client.Client;

public class AccountMediator implements AbstractAccountMediator {
    private Account senderAcc;
    private Account recipientAcc;
    private double sum;
    @Override
    public void addSenderAcc(Account acc) {
        this.senderAcc = acc;
    }

    @Override
    public void addRecipientAcc(Account acc) {
        this.recipientAcc = acc;
    }
    @Override
    public void addSum(double sum){
        this.sum = sum;
    }

    @Override
    public void notifyTransaction() {
        System.out.println(senderAcc.getAccountNumber() + " has transferred funds to " + recipientAcc.getAccountNumber() + " in value of " + sum);
    }
}

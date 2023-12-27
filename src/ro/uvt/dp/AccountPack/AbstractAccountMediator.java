package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.Account;
import ro.uvt.dp.Client.Client;

public interface AbstractAccountMediator {
    void addSenderAcc(Account acc);
    void addRecipientAcc(Account acc);
    void addSum(double sum);
    void notifyTransaction();
}

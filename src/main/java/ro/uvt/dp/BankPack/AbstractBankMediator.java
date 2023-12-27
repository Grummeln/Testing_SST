package ro.uvt.dp.BankPack;

import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;

public interface AbstractBankMediator {
    public void addBank(Bank bank);
    public void addClient(Client cl);
    public short notifyStatus();
}

package ro.uvt.dp.BankPack;

import ro.uvt.dp.BankPack.AbstractBankMediator;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;

public class BankMediator implements AbstractBankMediator {
    private Bank bank;
    private Client cl;
    @Override
    public void addBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void addClient(Client cl) {
        this.cl = cl;
    }

    @Override
    public short notifyStatus() {
        if(bank.getClient(cl.getName()) != null)
            System.out.println("Client " + cl.getName() + " is now part of the bank " + bank.toString());
        else
            System.out.println("Client " + cl.getName() + " is no longer part of the bank " + bank.toString());
        return 0;
    }
}

package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.Account;

public abstract class AccountDecorator extends Account {
    protected Account acc;

    public AccountDecorator(Account acc){this.acc = acc;}
}

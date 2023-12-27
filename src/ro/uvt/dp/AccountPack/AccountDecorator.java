package ro.uvt.dp.AccountPack;

public abstract class AccountDecorator extends Account {
    protected Account acc;

    public AccountDecorator(Account acc){this.acc = acc;}
}

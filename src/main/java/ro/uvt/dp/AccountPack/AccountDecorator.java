package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.Account;

public abstract class AccountDecorator extends Account {
    protected Account acc;

    public AccountDecorator(Account acc){this.acc = acc;}
    public String getAccountRepresentation() {
        StringBuilder representation = new StringBuilder(acc.toString());
        Account currentDecorator = acc;

        while (currentDecorator instanceof AccountDecorator) {
            currentDecorator = ((AccountDecorator) currentDecorator);
            representation.append("\n\t").append(currentDecorator.getClass().getSimpleName());
        }

        return representation.toString();
    }
}

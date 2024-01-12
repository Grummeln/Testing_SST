package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.AccountDecorator;
import ro.uvt.dp.AccountPack.Account;

public class ChildAccount extends AccountDecorator {
    public ChildAccount(Account acc){
        super(acc);
    }

    @Override
    public void transfer(Account c, double s) throws Exception {

    }

    @Override
    public String toString(){
        return acc.toString() + " This account is a child's account.";
    }

    @Override
    public void execute() throws Exception {

    }

    @Override
    public void undo() throws Exception {

    }

    @Override
    public void redo() throws Exception {

    }

    @Override
    public double getInterest() {
        return 0;
    }

}

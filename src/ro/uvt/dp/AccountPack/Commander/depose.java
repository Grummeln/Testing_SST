package ro.uvt.dp.AccountPack.Commander;

import ro.uvt.dp.AccountPack.Operations;
import ro.uvt.dp.AccountPack.Account;

public class depose implements Operations {
    Account mAcc;
    double mSum;

    public depose(Account acc, double sum){
        mAcc = acc;
        mSum = sum;
    }
    @Override
    public void execute() throws Exception {
        mAcc.depose(mSum);
    }

    @Override
    public void undo() throws Exception {
        mAcc.retrieve(mSum);
    }

    @Override
    public void redo() throws Exception {
        mAcc.depose(mSum);
    }

    @Override
    public double getTotalAmount() {
        return 0;
    }

    @Override
    public double getInterest() {
        return 0;
    }

    @Override
    public void depose(double amount) throws Exception {

    }

    @Override
    public void retrieve(double amount) throws Exception {

    }
}

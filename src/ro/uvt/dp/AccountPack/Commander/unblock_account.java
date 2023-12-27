package ro.uvt.dp.AccountPack.Commander;

import ro.uvt.dp.AccountPack.Account;
import ro.uvt.dp.AccountPack.Operations;

public class unblock_account implements Operations {
    Account mAcc;
    boolean oldBlockStatus;

    public unblock_account(Account acc){
        mAcc = acc;
    }


    @Override
    public void execute() {
        oldBlockStatus = mAcc.is_account_blocked();
        mAcc.unblock_account();
    }

    @Override
    public void undo() {
        if(oldBlockStatus)
            mAcc.block_account();
        if(!oldBlockStatus)
            mAcc.unblock_account();
    }

    @Override
    public void redo() {
        mAcc.unblock_account();
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

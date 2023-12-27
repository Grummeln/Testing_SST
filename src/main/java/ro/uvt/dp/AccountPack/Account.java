package ro.uvt.dp.AccountPack;
//unused imports

public abstract class Account implements ro.uvt.dp.AccountPack.Operations {

	protected String accountCode = null;
	protected boolean blockedFlag = false;
	protected boolean closedFlag = false;
	protected double amount = 0;

	protected Account() {
	}

	public void setClosed(boolean closedFlag) throws Exception {
		this.closedFlag = closedFlag;
		if(closedFlag){
			throw new Exception("Account closed.");
		}
	}

	public void setBlocked(boolean blockedFlag) throws Exception {
		this.blockedFlag = blockedFlag;
		if(blockedFlag){
			throw new Exception("Account blocked.");
		}

	}

	public enum TYPE { //static redundant
		EUR, RON
	};

	protected Account(String accountNumber, double sum) throws Exception {
		this.accountCode = accountNumber;
		depose(sum);
	}

	@Override
	public double getTotalAmount() {

		return amount + amount * getInterest();
	}

	@Override
	public void depose(double sum) throws Exception {
		if (blockedFlag) {
			throw new Exception("Account is currently blocked.");
		}
		if (closedFlag){
			throw new Exception("Account closed");
		}
			if( sum < 0) {
				throw new IllegalArgumentException("Hello, you've tried to add less than nothing somehow.");
			}
		this.amount += sum;
	}

	@Override
	public void retrieve(double sum) throws Exception {
		if (blockedFlag) {
			throw new Exception("Account is currently blocked.");
		}
		if (closedFlag){
			throw new Exception("Account closed");
		}
				if( sum > this.amount) {
					throw new IllegalArgumentException("Hello, you've tried to retrieve more than you currently own.");
				}
				if (sum < 0 ){
					throw new IllegalArgumentException("Cannot retrieve less than 0");
				}
		this.amount -= sum;

	}

	public abstract void transfer(Account c, double s) throws Exception;
	public boolean is_account_blocked(){return this.blockedFlag;}

	public void block_account(){this.blockedFlag = true;}

	public void unblock_account(){this.blockedFlag = false;}
	public String toString() {
		return "Account: code=" + accountCode + ", amount=" + amount;
	}

	public String getAccountNumber() {
		return accountCode;
	}

}

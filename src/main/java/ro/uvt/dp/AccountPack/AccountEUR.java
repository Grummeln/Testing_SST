package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.AbstractFactory;
import ro.uvt.dp.AccountPack.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountEUR extends Account implements ro.uvt.dp.AccountPack.Transfer {
	private static final Map<String, AccountEUR> accountMap = new HashMap<>();
	public static AccountEUR getInstance(String accountNumber, double sum) throws Exception {
		if (accountMap.containsKey(accountNumber)) {
			throw new Exception("Account with this number already exists.");
		} else {
			AccountEUR newAccount = new AccountEUR(accountNumber, sum);
			accountMap.put(accountNumber, newAccount);
			return newAccount;
		}
	}

	public AccountEUR(String accountNumber, double sum) throws Exception {
		super(accountNumber, sum);
	}
	@Override
	public void transfer(Account c, double s) throws Exception {
		if (s < 0){
			throw new IllegalArgumentException("Cannot transfer less than 0");
		}
		c.retrieve(s);
		depose(s);
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

	public double getInterest() {
		return 0.01;

	}
	public static class EURFactory implements AbstractFactory {
		@Override
		public Account createEURAccount(String accountNumber, double sum) throws Exception {
			//getInstance(accountNumber, sum);
			return new AccountEUR(accountNumber, sum);
		}

		@Override
		public Account createRONAccount(String accountNumber, double sum) throws Exception {
			return new ro.uvt.dp.AccountPack.AccountRON(accountNumber, sum);
		}
	}

	@Override
	public String toString() {
		return "Account EUR [" + super.toString() + "]";
	}
}

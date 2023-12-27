package ro.uvt.dp.AccountPack;

import java.util.HashMap;
import java.util.Map;

public class AccountRON extends Account implements Transfer {
	private static final Map<String, AccountRON> accountMap = new HashMap<>();
	public AccountRON(String accountNumber, double sum) throws Exception {
		super(accountNumber, sum);
	}
	public static AccountRON getInstance(String accountNumber, double sum) throws Exception {
		if (accountMap.containsKey(accountNumber)) {
			throw new Exception("Account with this number already exists.");
		} else {
			AccountRON newAccount = new AccountRON(accountNumber, sum);
			accountMap.put(accountNumber, newAccount);
			return newAccount;
		}
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
		if (amount < 500)
			return 0.03;
		else
			return 0.08;

	}

	@Override
	public String toString() {
		return "Account RON [" + super.toString() + "]";
	}

	@Override
	public void transfer(Account c, double s) throws Exception {
		c.retrieve(s);
		depose(s);
	}
	public static class RONFactory implements AbstractFactory {
		@Override
		public Account createEURAccount(String accountNumber, double sum) throws Exception {
			return new AccountEUR(accountNumber, sum);
		}

		@Override
		public Account createRONAccount(String accountNumber, double sum) throws Exception {
			//getInstance(accountNumber, sum);
			return new AccountRON(accountNumber, sum);
		}
	}
}

package ro.uvt.dp.AccountPack;

import ro.uvt.dp.AccountPack.Account;

public interface Transfer {
	public void transfer(Account c, double s) throws Exception;
}

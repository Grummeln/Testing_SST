package ro.uvt.dp.test;

import ro.uvt.dp.AccountPack.*;

import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.*;

import java.util.LinkedList;

public class Test {
	public static void main(String[] args) throws Exception {
		LinkedList<Client> clients = new LinkedList<>();
		Bank bcr = new Bank(clients, "Banca BCR");
		AbstractFactory EURFactory = new AccountEUR.EURFactory();
		AbstractFactory RONFactory = new AccountRON.RONFactory();
		Client.ClientBuilder builder1 = new Client.ClientBuilder("Ionescu")
				.age(30)
				.phoneNumber(1234)
				.address("Susan street")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		Client cl1 = builder1.build();
		cl1.addAccount(Account.TYPE.RON, "RON1234", 400);
		bcr.addClient(cl1);

		Client.ClientBuilder builder2 = new Client.ClientBuilder("Marinescu Marin")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		Client cl2 = builder2.build();
		cl2.addAccount(Account.TYPE.EUR, "EUR231", 200);
		cl2.addAccount(Account.TYPE.RON, "RON679", 7000);
		bcr.addClient(cl2);
		Client.ClientBuilder builder3 = new Client.ClientBuilder("Testescu Testan")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		Client cl3 = builder3.build();



		Client.ClientBuilder builder4 = new Client.ClientBuilder("Yipiiee Testan")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		Client cl4 = builder4.build();

		bcr.addClient(cl4);
		System.out.println(bcr);

	}
}

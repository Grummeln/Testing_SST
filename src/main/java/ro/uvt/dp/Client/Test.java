package ro.uvt.dp.Client;

import ro.uvt.dp.AccountPack.*;

import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.*;

import java.util.LinkedList;

public class Test {
	public static void main(String[] args) throws Exception {
		LinkedList<ro.uvt.dp.Client.Client> clients = new LinkedList<>();
		Bank bcr = new Bank(clients, "Banca BCR");
		ro.uvt.dp.AccountPack.AbstractFactory EURFactory = new ro.uvt.dp.AccountPack.AccountEUR.EURFactory();
		ro.uvt.dp.AccountPack.AbstractFactory RONFactory = new ro.uvt.dp.AccountPack.AccountRON.RONFactory();
		ro.uvt.dp.Client.Client.ClientBuilder builder1 = new ro.uvt.dp.Client.Client.ClientBuilder("Ionescu")
				.age(30)
				.phoneNumber(1234)
				.address("Susan street")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		ro.uvt.dp.Client.Client cl1 = builder1.build();
		cl1.addAccount(ro.uvt.dp.AccountPack.Account.TYPE.RON, "RON1234", 400);
		bcr.addClient(cl1);

		ro.uvt.dp.Client.Client.ClientBuilder builder2 = new ro.uvt.dp.Client.Client.ClientBuilder("Marinescu Marin")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		ro.uvt.dp.Client.Client cl2 = builder2.build();
		cl2.addAccount(ro.uvt.dp.AccountPack.Account.TYPE.EUR, "EUR231", 200);
		cl2.addAccount(ro.uvt.dp.AccountPack.Account.TYPE.RON, "RON679", 7000);
		bcr.addClient(cl2);
		ro.uvt.dp.Client.Client.ClientBuilder builder3 = new ro.uvt.dp.Client.Client.ClientBuilder("Testescu Testan")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		ro.uvt.dp.Client.Client cl3 = builder3.build();



		ro.uvt.dp.Client.Client.ClientBuilder builder4 = new ro.uvt.dp.Client.Client.ClientBuilder("Yipiiee Testan")
				.address("Hoi")
				.abstractFactory(RONFactory)
				.abstractFactory(EURFactory);
		ro.uvt.dp.Client.Client cl4 = builder4.build();

		bcr.addClient(cl4);
		System.out.println(bcr);

	}
}

package ro.uvt.dp.Client;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ro.uvt.dp.AccountPack.*;
import ro.uvt.dp.AccountPack.Account.TYPE;

public class Client {
	private final TYPE type;
	private final int sum;
	private String name;
	private int age;
	private String address;
	private Date dateOfBirth;
	private String placeOfBirth;
	private double phoneNumber;
	private List<Account> accounts = new LinkedList<>();
	private int accountsNr = 0;
	private String accountNumber;
	private AbstractFactory abstractFactory;

	public Client(ClientBuilder clientBuilder) throws Exception {
		this.name = clientBuilder.name;
		this.address = clientBuilder.address;
		this.phoneNumber = clientBuilder.phoneNumber;
		this.dateOfBirth = clientBuilder.dateOfBirth;
		this.placeOfBirth = clientBuilder.placeOfBirth;
		this.type = clientBuilder.type;
		this.accountNumber = clientBuilder.accountNumber;
		this.sum = clientBuilder.sum;
		this.age = clientBuilder.age;
		this.abstractFactory = clientBuilder.abstractFactory;
		addAccount(type, accountNumber, sum);
	}

	public static class ClientBuilder{
		private String name;
		private String address;
		private int age;
		private double phoneNumber;
		private Date dateOfBirth;
		private String placeOfBirth;
		private TYPE type;
		private String accountNumber;
		private int sum;
		private AbstractFactory abstractFactory;
		public ClientBuilder(String name){
				this.name = name;
		}
		public ClientBuilder age(int age){
			this.age = age;
			return this;
		}
		public ClientBuilder address(String address){
			this.address = address;
			return this;
		}
		public ClientBuilder accountNumber(String accountNumber){
			this.accountNumber = accountNumber;
			return this;
		}
		public ClientBuilder type(TYPE type){
			this.type = type;
			return this;
		}

		public ClientBuilder sum(int sum){
			this.sum = sum;
			return this;
		}
		public ClientBuilder phoneNumber(double phoneNumber){
			this.phoneNumber = phoneNumber;
			return this;
		}
		public ClientBuilder abstractFactory(AbstractFactory abstractFactory){
			this.abstractFactory = abstractFactory;
			return this;
		}
		public ClientBuilder dateOfBirth(Date dateOfBirth){
			this.dateOfBirth = dateOfBirth;
			return this;
		}
		public ClientBuilder placeOfBirth(String placeOfBirth){
			this.placeOfBirth= placeOfBirth;
			return this;
		}
		public Client build() throws Exception {
			Client client = new Client(this);
			return client;
		}

	}

	public void addAccount(TYPE type, String accountNumber, double sum) throws Exception {
		Account c = null;
		if (type == Account.TYPE.EUR)
			c = createEURAccount(accountNumber, sum);
		else if (type == Account.TYPE.RON)
			c = createRONAccount(accountNumber, sum);
		if( c!= null) {
			accounts.add(accountsNr++, c);
		}
	}
	public Account createEURAccount(String accountNumber, double sum) throws Exception {
		AccountEUR.getInstance(accountNumber, sum);
		return abstractFactory.createEURAccount(accountNumber, sum);
	}

	public Account createRONAccount(String accountNumber, double sum) throws Exception {
		AccountRON.getInstance(accountNumber, sum);
		return abstractFactory.createRONAccount(accountNumber, sum);
	}
	public Account getAccount(String accountCode) {
		for (int i = 0; i < accountsNr; i++) {
			if (accounts.get(i).getAccountNumber().equals(accountCode)) {
				return accounts.get(i);
			}
		}
		return null;
	}
	public void closeAccount(String accountNumber) {
		Iterator<Account> iterator = accounts.iterator();
		while (iterator.hasNext()) {
			Account account = iterator.next();
			if (account.getAccountNumber().equals(accountNumber)) {
				iterator.remove();
				System.out.println("Account " + accountNumber + " has been deleted.");
				return;
			}
		}
		System.out.println("Account " + accountNumber + " not found.");
	}

	@Override
	public String toString() {
		return "\n\tClient [name= " + name + ", address=  " + address + ",age = " + age +" ,phone Number = " + phoneNumber + " ,date Of Birth = " + dateOfBirth +  " ,place of Birth = " + placeOfBirth +  ", acounts= " + accounts.toString() + "]";
	}

	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}

	public String getAddress() {
		return address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}
}

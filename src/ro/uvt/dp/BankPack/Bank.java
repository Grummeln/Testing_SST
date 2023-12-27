package ro.uvt.dp.BankPack;

import ro.uvt.dp.Client.Client;

import java.util.Iterator;
import java.util.LinkedList;

public class Bank {

	private LinkedList<Client> clients;
	private int clientsNumber;
	private String bankCode = null;

	public Bank(LinkedList<Client> clients, String bankNumber) {
		this.clients = clients;
		this.bankCode = bankNumber;
	}

	public void addClient(Client c) {
		clients.add(c);
		clientsNumber++;
	}

	
	public Client getClient(String clientName) {
		for (int i = 0; i < clientsNumber; i++) {
			if (clients.get(i).getName().equals(clientName)) {
				return clients.get(i);
			}

		}
		return null;
	}
	public void removeClient(Client client) {
		Iterator<Client> iterator = clients.iterator();
		while (iterator.hasNext()) {
			Client c = iterator.next();
			if (c.equals(client)) {
				iterator.remove();
				System.out.println("Client " + client.getName() + " has been removed.");
				return;
			}
		}
		System.out.println("Client not found.");
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bank [code=").append(bankCode).append(", clients=\n");

		// Loop through the filled slots in the clients array
		for (int i = 0; i < clientsNumber; i++) {
			builder.append("\tClient ").append(i + 1).append(":\n");
			builder.append("\t").append(clients.get(i).toString().replaceAll("\n", "\n\t")).append("\n");
		}

		builder.append("]");
		return builder.toString();
	}
}

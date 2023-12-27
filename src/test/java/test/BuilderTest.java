package test;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.AccountPack.Account;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;
import ro.uvt.dp.AccountPack.AbstractFactory;
import ro.uvt.dp.AccountPack.AccountEUR;
import ro.uvt.dp.AccountPack.AccountRON;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.LinkedList;

public class BuilderTest {
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "Banca BCR");
    AbstractFactory EURFactory = new AccountEUR.EURFactory();
    AbstractFactory RONFactory = new AccountRON.RONFactory();
    @Test
    public void testClientBuilder() throws Exception {
        String name = "John Doe";
        String address = "123 Main St";
        int age = 35;
        double phoneNumber = 1234567890;
        Date dateOfBirth = new Date();
        String placeOfBirth = "City";
        Client.ClientBuilder clientBuilder = new Client.ClientBuilder(name);
        clientBuilder.address(address);
        clientBuilder.age(age);
        clientBuilder.phoneNumber(phoneNumber);
        clientBuilder.dateOfBirth(dateOfBirth);
        clientBuilder.placeOfBirth(placeOfBirth);

        Client client = clientBuilder.build();

        assertEquals(name, client.getName());
        assertEquals(address, client.getAddress());
        assertEquals(age, client.getAge());
        assertEquals(phoneNumber, client.getPhoneNumber());
        assertEquals(dateOfBirth, client.getDateOfBirth());
        assertEquals(placeOfBirth, client.getPlaceOfBirth());
    }
    @Test
    public void testAddEURAccount() throws Exception {

        Client.ClientBuilder builder1 = new Client.ClientBuilder("Ionescu")
                .age(30)
                .phoneNumber(1234)
                .address("Susan street")
                .abstractFactory(RONFactory)
                .abstractFactory(EURFactory);
        Client cl1 = builder1.build();
        cl1.addAccount(Account.TYPE.RON, "RON1234", 400);
        bcr.addClient(cl1);
        assertNotNull(builder1.accountNumber("RON1234"));
    }

    @Test
    public void testAddRONAccount() throws Exception {
        Client.ClientBuilder client2 = new Client.ClientBuilder("John Doe")
                .age(30)
                .address("123 Main St")
                .phoneNumber(1234567890)
                .dateOfBirth(new Date())
                .placeOfBirth("City")
                .type(Account.TYPE.EUR)
                .accountNumber("RON0001")
                .abstractFactory(RONFactory);
        Client cl2 = client2.build();
        cl2.addAccount(Account.TYPE.RON, "RON0001", 400);
        bcr.addClient(cl2);

        cl2.addAccount(Account.TYPE.RON, "RON0002", 1500);
        assertNotNull(client2.accountNumber("RON0002"));
    }

    @Test
    public void testCloseNonExistingAccount() throws Exception {
        Client client = new Client.ClientBuilder("John Doe")
                .age(30)
                .address("123 Main St")
                .phoneNumber(1234567890)
                .dateOfBirth(new Date())
                .placeOfBirth("City")
                .type(Account.TYPE.EUR)
                .accountNumber("EUR0003")
                .abstractFactory(EURFactory)
                .build();

        client.closeAccount("InvalidAcc");
        assertNull(client.getAccount("InvalidAcc"));
    }
}

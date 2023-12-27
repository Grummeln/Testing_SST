package test;
import ro.uvt.dp.AccountPack.*;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;


public class DecoratorTest {
    AbstractFactory EURFactory = new AccountEUR.EURFactory();
    AbstractFactory RONFactory = new AccountRON.RONFactory();
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "BCR");
    @Test
    public void addLifeInsurance() throws Exception {
        String s1 = "Account RON [Account: code=insurance1, amount=100.0]";
        String s2 = "Account RON [Account: code=insurance1, amount=100.0]\n\tThis account has insurance.";
        Account lifeAcc = RONFactory.createRONAccount("insurance1", 100);
        assert lifeAcc != null;
        assertEquals(lifeAcc.toString(), s1);
        lifeAcc = new LifeInsurance(lifeAcc);
        assertEquals(lifeAcc.toString(), s2);
    }

    @Test
    public void addEconomy() throws Exception {
        String s1 = "Account EUR [Account: code=economy1, amount=100.0]";
        String s2 = "Account EUR [Account: code=economy1, amount=100.0]\n\tThis is an economy account.";
        Account econAcc = EURFactory.createEURAccount("economy1", 100);
        assert econAcc != null;
        assertEquals(econAcc.toString(), s1);
        econAcc = new EconomyAccount(econAcc);
        assertEquals(econAcc.toString(), s2);
    }
    @Test
    public void addChildAccount() throws Exception {
        String s1 = "Account EUR [Account: code=child1, amount=100.0]";
        String s2 = "Account EUR [Account: code=child1, amount=100.0]\n\tThis is an economy account.";
        Account childAcc = EURFactory.createEURAccount("child1", 100);
        assert childAcc != null;
        assertEquals(childAcc.toString(), s1);
        childAcc = new EconomyAccount(childAcc);
        assertEquals(childAcc.toString(), s2);
    }
    @Test
    public void multipleDecorators() throws Exception {
        String s1 = "Account EUR [Account: code=both1, amount=100.0]";
        String s2 = "Account EUR [Account: code=both1, amount=100.0]\n\tThis is an economy account.\n\tThis account has insurance.";
        Account bothAcc = EURFactory.createEURAccount("both1", 100);
        assert bothAcc != null;
        assertEquals(bothAcc.toString(), s1);
        bothAcc = new EconomyAccount(bothAcc);
        bothAcc = new LifeInsurance(bothAcc);
        assertEquals(bothAcc.toString(), s2);
    }
}
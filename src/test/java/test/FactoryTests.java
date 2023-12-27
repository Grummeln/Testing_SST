package test;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.AccountPack.Account;
import ro.uvt.dp.AccountPack.AbstractFactory;
import ro.uvt.dp.AccountPack.AccountEUR;
import ro.uvt.dp.AccountPack.AccountRON;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryTests {

    @Test
    public void testCreateEURAccount() throws Exception {
        AbstractFactory factory = new AccountEUR.EURFactory();
        Account account = factory.createEURAccount("EUR123", 1000);
        assertTrue(account instanceof AccountEUR);
    }

    @Test
    public void testCreateRONAccount() throws Exception {
        AbstractFactory factory = new AccountRON.RONFactory();
        Account account = factory.createRONAccount("RON123", 1000);
        assertTrue(account instanceof AccountRON);
    }

}

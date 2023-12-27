package ro.uvt.dp.test;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.AccountPack.*;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    AbstractFactory EURFactory = new AccountEUR.EURFactory();
    AbstractFactory RONFactory = new AccountRON.RONFactory();
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "BCR");

    @Test
    public void testDepose() throws Exception {
        Account account = EURFactory.createEURAccount("EUR1234", 1015);
        account.depose(500);
        assertEquals(1530.15, account.getTotalAmount(), 0.0001);
    }

    @Test
    public void testRetrieve() throws Exception {
        Account account = EURFactory.createEURAccount("EUR13", 1000);
        account.retrieve(500);
        assertEquals(505, account.getTotalAmount(), 0.0001);
    }

    @Test
    public void testTransfer() throws Exception {
        Account account = EURFactory.createEURAccount("EUR113", 1000);
        Account account1 = EURFactory.createEURAccount("EUR1037", 200);
        account.transfer(account1, 40);
        assertEquals(161.6, account1.getTotalAmount(), 0.0001);
    }

    @Test
    public void testClosedAccountSetClosed() throws Exception {
        Account account = EURFactory.createEURAccount("EUR127", 1000);
        Exception exception = assertThrows(Exception.class, () -> account.setClosed(true));
        assertEquals("Account closed.", exception.getMessage());
    }


    @Test
    public void testClosedAccountDepose() throws Exception {
        Account account = EURFactory.createEURAccount("EUR23", 1000);
        try {
            account.setClosed(true);
            account.depose(200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account closed.", e.getMessage());
        }
    }

    @Test
    public void testClosedAccountRetrieve() throws Exception {
        Account account =EURFactory.createEURAccount("EUR120", 1000);
        try {
            account.setClosed(true);
            account.retrieve(200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account closed.", e.getMessage());
        }
    }
    @Test
    public void testAccountClosedTransfer() throws Exception {
        Account account =EURFactory.createEURAccount("EUR1230", 1000);
        Account account1 =EURFactory.createEURAccount("EUR55400", 200);
        try {
            account.setClosed(true);
            account.transfer(account1, 200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account closed.", e.getMessage());
        }
    }
    @Test
    public void testSetAccountBlocked() throws Exception {
        Account account =EURFactory.createEURAccount("EUR143", 1000);
        Exception exception = assertThrows(Exception.class, () -> account.setBlocked(true));
        assertEquals("Account blocked.", exception.getMessage());
    }
    @Test
    public void testAccountBlockedDepose() throws Exception {
        Account account = EURFactory.createEURAccount("EUR723", 1000);
        try {
            account.setBlocked(true);
            account.depose(200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account blocked.", e.getMessage());
        }
    }
    @Test
    public void testAccountBlockedRetrieve() throws Exception {
        Account account = EURFactory.createEURAccount("EUR823", 1000);
        try {
            account.setBlocked(true);
            account.retrieve(200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account blocked.", e.getMessage());
        }
    }
    @Test
    public void testAccountBlockedTransfer() throws Exception {
        Account account =EURFactory.createEURAccount("EUR023", 25);
        Account account1 =EURFactory.createEURAccount("EUR554", 200);
        try {
            account.setBlocked(true);
            account.transfer(account1, 200);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Account blocked.", e.getMessage());
        }
    }
    @Test
    public void testInsufficientFundTransfer() throws Exception {
        Account account =EURFactory.createEURAccount("EUR137", 1000);
        Account account1 =EURFactory.createEURAccount("EUR321", 20);
        try {
            account1.transfer(account, 2000);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Hello, you've tried to retrieve more than you currently own.", e.getMessage());
        }
    }
    @Test
    public void testInsufficientFundRetrieve() throws Exception {
        Account account =EURFactory.createEURAccount("EUR33", 1000);
        try {
            account.retrieve(2000);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Hello, you've tried to retrieve more than you currently own.", e.getMessage());
        }
    }
    @Test
    public void testIllegalFundDeposit() throws Exception {
        Account account =EURFactory.createEURAccount("EUR103", 1000);
        try {
            account.depose(-2000);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Hello, you've tried to add less than nothing somehow.", e.getMessage());
        }
    }
    @Test
    public void testIllegalFundRetrieve() throws Exception {
        Account account =EURFactory.createEURAccount("EUR1275", 1000);
        try {
            account.retrieve(-2000);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Cannot retrieve less than 0", e.getMessage());
        }
    }
    @Test
    public void testIllegalFundTransfer() throws Exception {
        Account account =EURFactory.createEURAccount("EUR1213", 1000);
        Account account1 =EURFactory.createEURAccount("EUR32231", 200);
        try {
            account1.transfer(account,-2000);
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Cannot transfer less than 0", e.getMessage());
        }
    }
    @Test
    public void testUniqueAccountNumbers() {
        String accountNumber = "RON123";
        String duplicateAccountNumber = "RON123";

        try {
            AccountRON instance1 = AccountRON.getInstance(accountNumber, 1000);
            AccountRON instance2 = AccountRON.getInstance(duplicateAccountNumber, 1000);
            assertSame(instance1, instance2);
        } catch (Exception e) {
            assertEquals("Account with this number already exists.", e.getMessage());
        }
    }
    @Test
    public void testCreateAccountWithMinimumValues() throws Exception {
        AbstractFactory factory = new AccountEUR.EURFactory();
        Account account = factory.createEURAccount("EUR1", 0);
        assertEquals(0, account.getTotalAmount(), 0.0001);
    }


}

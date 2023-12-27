package ro.uvt.dp.test;
import ro.uvt.dp.AccountPack.*;
import ro.uvt.dp.AccountPack.Commander.*;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CommandTests {
    AbstractFactory EURFactory = new AccountEUR.EURFactory();
    AbstractFactory RONFactory = new AccountRON.RONFactory();
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "BCR");
    @Test
    void blockAccountTest() throws Exception {
        Manager om = new Manager();
        Account commandAcc = RONFactory.createRONAccount("commandAcc1", 100);
        assertNotNull(commandAcc);
        Operations o1 = new block_account(commandAcc);
        Operations o2 = new unblock_account(commandAcc);
        assertFalse(commandAcc.is_account_blocked());
        om.executeOp(o1);
        assertTrue(commandAcc.is_account_blocked());
        om.undo();
        assertFalse(commandAcc.is_account_blocked());
        om.executeOp(o2);
        assertFalse(commandAcc.is_account_blocked());
        om.undo();
        assertFalse(commandAcc.is_account_blocked());
    }

    @Test
    void retrieveDeposeTest() throws Exception {
        Manager om = new Manager();
        Account commandAcc = RONFactory.createRONAccount( "commandAcc2", 100);
        Operations o1 = new depose(commandAcc, 30);
        Operations o2 = new retrieve(commandAcc, 20);
        assertNotNull(commandAcc);
        assertEquals(commandAcc.getTotalAmount(), 103);
        om.executeOp(o1);
        om.executeOp(o1);
        om.executeOp(o2);
        om.executeOp(o1);
        assertEquals(commandAcc.getTotalAmount(), 175.1);
        om.undo();
        om.undo();
        assertEquals(commandAcc.getTotalAmount(), 164.8);
        om.redo();
        assertEquals(commandAcc.getTotalAmount(), 144.2);
    }
    @Test
    void testUndoRedoWithDifferentSequences() throws Exception {
        Manager om = new Manager();
        Account commandAcc = RONFactory.createRONAccount("commandAcc3", 100);

        Operations o1 = new depose(commandAcc, 30);
        Operations o2 = new retrieve(commandAcc, 20);

        assertNotNull(commandAcc);

        // Perform operations with different sequences
        om.executeOp(o1);
        om.executeOp(o2);
        om.undo();
        assertEquals(133.9, commandAcc.getTotalAmount(), 0.0001);

        om.executeOp(o1);
        om.executeOp(o2);
        om.undo();
        om.redo();
        assertEquals(144.2, commandAcc.getTotalAmount(), 0.0001);
    }

}
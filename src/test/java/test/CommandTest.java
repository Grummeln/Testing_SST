package test;
import ro.uvt.dp.AccountPack.*;

import org.junit.jupiter.api.Test;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;
import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;


public class CommandTest {
    AbstractFactory EURFactory = new AccountEUR.EURFactory();
    AbstractFactory RONFactory = new AccountRON.RONFactory();
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "BCR");
    @Test
    public void blockAccountTest() throws Exception {
        ro.uvt.dp.AccountPack.Commander.Manager om = new ro.uvt.dp.AccountPack.Commander.Manager();
        Account commandAcc = RONFactory.createRONAccount("commandAcc1", 100);
        assertNotNull(commandAcc);
        Operations o1 = new ro.uvt.dp.AccountPack.Commander.block_account(commandAcc);
        Operations o2 = new ro.uvt.dp.AccountPack.Commander.unblock_account(commandAcc);
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
    public void retrieveDeposeTest() throws Exception {
        ro.uvt.dp.AccountPack.Commander.Manager om = new ro.uvt.dp.AccountPack.Commander.Manager();
        Account commandAcc = RONFactory.createRONAccount( "commandAcc2", 100);
        Operations o1 = new ro.uvt.dp.AccountPack.Commander.depose(commandAcc, 30);
        Operations o2 = new ro.uvt.dp.AccountPack.Commander.retrieve(commandAcc, 20);
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
    public void testUndoRedoWithDifferentSequences() throws Exception {
        ro.uvt.dp.AccountPack.Commander.Manager om = new ro.uvt.dp.AccountPack.Commander.Manager();
        Account commandAcc = RONFactory.createRONAccount("commandAcc3", 100);

        Operations o1 = new ro.uvt.dp.AccountPack.Commander.depose(commandAcc, 30);
        Operations o2 = new ro.uvt.dp.AccountPack.Commander.retrieve(commandAcc, 20);

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

package test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import ro.uvt.dp.AccountPack.*;
import ro.uvt.dp.AccountPack.Commander.Manager;
import ro.uvt.dp.BankPack.Bank;
import ro.uvt.dp.Client.Client;

import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

    private Account sourceAccount;
    private Account destinationAccount;
    private Exception thrownException;

    private Client.ClientBuilder clientBuilder;
    private Client client;
    private String accountNumberToClose;
    private final Manager commandManager = new Manager();
    private Account commandAcc;
    private double initialBalance;
    private double currentBalance;
    LinkedList<Client> clients = new LinkedList<>();
    Bank bcr = new Bank(clients, "BCR");
    private Account decoratedAcc;
    private String accNumber;


    @Given("the EUR account with number {string} exists with a balance of {double} EUR")
    public void createAccountWithBalance(String accountNumber, double initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("{double} EUR is deposited into the account")
    public void depositMoney(double amount) {
        try {
            sourceAccount.depose(amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the account balance should be {double} EUR")
    public void verifyAccountBalance(double expectedBalance) {
        assertEquals(expectedBalance, sourceAccount.getTotalAmount(), 0.0001);
    }

    @When("{double} EUR is retrieved from the account")
    public void retrieveMoney(double amount) {
        try {
            sourceAccount.retrieve(amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("the source EUR account with number {string} exists with a balance of {double} EUR")
    public void createSourceAccountWithBalance(String sourceAccountNumber, double initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(sourceAccountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @And("the destination EUR account with number {string} exists with a balance of {double} EUR")
    public void createDestinationAccountWithBalance(String destAccountNumber, double initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            destinationAccount = EURFactory.createEURAccount(destAccountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("{double} EUR is transferred from the source account to the destination account")
    public void transferMoney(double amount) {
        try {
            sourceAccount.transfer(destinationAccount, amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("the account is set to closed")
    public void closeAccount() {
        try {
            sourceAccount.setClosed(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("an exception {string} should be thrown when attempting any operation")
    public void verifyException(String expectedExceptionMessage) {
        assertNotNull(thrownException);
        assertEquals(expectedExceptionMessage, thrownException.getMessage());
        thrownException = null;
    }


    @When("the account is set to blocked")
    public void setAccountBlocked() {
        try {
            sourceAccount.setBlocked(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("an attempt is made to retrieve {double} EUR from the account")
    public void attemptIllegalRetrieve(double amount) {
        try {
            sourceAccount.retrieve(amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the destination account balance should be {double} EUR")
    public void theDestinationAccountBalanceShouldBeEUR(double expectedBalance) {
        assertEquals(expectedBalance, destinationAccount.getTotalAmount(), 0.0001);
    }

    @Given("the closed EUR account with number {string} exists with a balance of {int} EUR")
    public void theClosedEURAccountWithNumberExistsWithABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            sourceAccount.setClosed(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("{int} EUR is attempted to be deposited into the account")
    public void eurIsAttemptedToBeDepositedIntoTheAccount(int amount) {
        try {
            sourceAccount.depose(amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("an exception {string} should be thrown")
    public void anExceptionShouldBeThrown(String expectedExceptionMessage) {
        assertNotNull(thrownException);
        assertEquals(expectedExceptionMessage, thrownException.getMessage());
        thrownException = null;
    }

    @Given("the source closed EUR account with number {string} exists with a balance of {int} EUR")
    public void theSourceClosedEURAccountWithNumberExistsWithABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            sourceAccount.setClosed(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("the blocked EUR account with number {string} exists with a balance of {int} EUR")
    public void theBlockedEURAccountWithNumberExistsWithABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            sourceAccount.setBlocked(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("the source blocked EUR account with number {string} exists with a balance of {int} EUR")
    public void theSourceBlockedEURAccountWithNumberExistsWithABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            sourceAccount.setBlocked(true);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("an EUR account with number {string} already exists with a balance of {int} EUR")
    public void anEURAccountWithNumberAlreadyExistsWithABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            this.client = new Client.ClientBuilder("John Doe")
                    .age(30).build();
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            this.client.addAccount(Account.TYPE.EUR, accountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("an attempt is made to create another EUR account with the same number {string} and a balance of {int} EUR")
    public void anAttemptIsMadeToCreateAnotherEURAccountWithTheSameNumberAndABalanceOfEUR(String accountNumber,int initialBalance) {

        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            sourceAccount = EURFactory.createEURAccount(accountNumber, initialBalance);
            this.client.addAccount(Account.TYPE.EUR, accountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("an attempt is made to create an EUR account with number {string} and a balance of {int} EUR")
    public void anAttemptIsMadeToCreateAnEURAccountWithNumberAndABalanceOfEUR(String accountNumber, int initialBalance) {
        try {
            AbstractFactory EURFactory = new AccountEUR.EURFactory();
            destinationAccount = EURFactory.createEURAccount(accountNumber, initialBalance);

        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the account should be created with a balance of {int} EUR")
    public void theAccountShouldBeCreatedWithABalanceOfEUR(int expectedBalance) {
        assertEquals(expectedBalance, destinationAccount.getTotalAmount(), 0.0001);
    }

    //Client Builder from here
    @Given("a client with the name {string} is being created using the ClientBuilder")
    public void createClient(String clientName) {
        clientBuilder = new Client.ClientBuilder(clientName);
    }

    @Given("the client's address is set to {string}")
    public void setClientAddress(String address) {
        clientBuilder.address(address);
    }

    @Given("the client's age is set to {int}")
    public void setClientAge(int age) {
        clientBuilder.age(age);
    }

    @Given("the client's phone number is set to {long}")
    public void setClientPhoneNumber(long phoneNumber) {
        clientBuilder.phoneNumber(phoneNumber);
    }

    @Given("the client's place of birth is set to {string}")
    public void setClientPlaceOfBirth(String placeOfBirth) {
        clientBuilder.placeOfBirth(placeOfBirth);
    }

    @When("the client is built")
    public void buildClient() throws Exception {
        client = clientBuilder.build();
    }

    @Then("the client should have the name {string}")
    public void verifyClientName(String expectedName) {
        assertEquals(expectedName, client.getName());
    }

    @Then("the client's address should be {string}")
    public void verifyClientAddress(String expectedAddress) {
        assertEquals(expectedAddress, client.getAddress());
    }

    @Then("the client's age should be {int}")
    public void verifyClientAge(int expectedAge) {
        assertEquals(expectedAge, client.getAge());
    }

    @Then("the client's phone number should be {long}")
    public void verifyClientPhoneNumber(long expectedPhoneNumber) {
        assertEquals(expectedPhoneNumber, client.getPhoneNumber());
    }

    @Then("the client's date of birth should be set")
    public void verifyClientDateOfBirth() {
        assertNotNull(client.getDateOfBirth());
    }

    @Then("the client's place of birth should be {string}")
    public void verifyClientPlaceOfBirth(String expectedPlaceOfBirth) {
        assertEquals(expectedPlaceOfBirth, client.getPlaceOfBirth());
    }

    @Given("a client named {string} is being created using the ClientBuilder")
    public void createNamedClient(String clientName) {
        createClient(clientName);
    }

    @Given("an EUR account with the number {string} and an initial balance of {int} is added to the client")
    public void addEurAccountToClient(String accountNumber, int initialBalance) {
        try {
            this.accNumber = accountNumber;
            this.client.addAccount(Account.TYPE.EUR, this.accNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("the client is added to the BCR bank")
    public void addClientToBCRBank() {
        bcr.addClient(client);
    }

    @Then("the client should have an account with the number {string}")
    public void verifyClientHasAccount(String expectedAccountNumber) {
       assertEquals(accNumber,expectedAccountNumber);
    }

    @Then("the client should not have an account with the number {string}")
    public void verifyClientDoesNotHaveAccount(String accountNumber) {
        assertNull(this.client.getAccount(accountNumber));
    }

    @When("the client attempts to close an account with the number {string}")
    public void attemptToCloseAccount(String accountNumber) {
        accountNumberToClose = accountNumber;
        try {
            client.closeAccount(accountNumber);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @And("a RON account with the number {string} and an initial balance of {int} is added to the client")
    public void aRONAccountWithTheNumberAndAnInitialBalanceOfIsAddedToTheClient(String accountNumber, int initialBalance) {
        try {
            this.accNumber = accountNumber;
            client.addAccount(Account.TYPE.RON, accNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @And("another RON account with the number {string} and an initial balance of {int} is added to the client")
    public void anotherRONAccountWithTheNumberAndAnInitialBalanceOfIsAddedToTheClient(String accountNumber, int initialBalance) {
        try {
            client.addAccount(Account.TYPE.RON, accountNumber, initialBalance);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @And("the client has an EUR account with the number {string}")
    public void theClientHasAnEURAccountWithTheNumber(String accountNumber) {
        assertNotNull(client.getAccount(accountNumber));
    }

    //Commander from here
    @Given("a bank named {string}")
    public void aBankNamed(String bankName) {
        // Assuming the bank creation logic is already implemented
    }

    @Given("a RON account with number {string} and an initial balance of {int} exists")
    public void aRONAccountWithNumberAndAnInitialBalanceOfExists(String accountNumber, int initialBalance) throws Exception {
        AbstractFactory RONFactory = new AccountRON.RONFactory();
        this.commandAcc = RONFactory.createRONAccount(accountNumber, initialBalance);
        assertNotNull(commandAcc);
        this.initialBalance = initialBalance;
        this.currentBalance = initialBalance;
    }

    @And("a command manager is created")
    public void aCommandManagerIsCreated() {

    }

    @When("the account is blocked")
    public void theAccountIsBlocked() throws Exception {
        Operations blockOperation = new ro.uvt.dp.AccountPack.Commander.block_account(commandAcc);
        commandManager.executeOp(blockOperation);
    }

    @Then("the account should be blocked")
    public void theAccountShouldBeBlocked() {
        assertTrue(commandAcc.is_account_blocked());
    }

    @When("the account is unblocked")
    public void theAccountIsUnblocked() throws Exception {
        Operations unblockOperation = new ro.uvt.dp.AccountPack.Commander.unblock_account(commandAcc);
        commandManager.executeOp(unblockOperation);
    }

    @Then("the account should not be blocked")
    public void theAccountShouldNotBeBlocked() {
        assertFalse(commandAcc.is_account_blocked());
    }

    @And("the actions should be undoable")
    public void theActionsShouldBeUndoable() throws Exception {
        commandManager.undo();
    }

    @And("the actions should be redoable")
    public void theActionsShouldBeRedoable() throws Exception {
        commandManager.redo();
    }

    @When("{int} RON is deposited into the account")
    public void RONIsDepositedIntoTheAccount(int depositAmount) throws Exception {
        Operations depositOperation = new ro.uvt.dp.AccountPack.Commander.depose(commandAcc, depositAmount);
        commandManager.executeOp(depositOperation);
        currentBalance += depositAmount;
    }

    @And("the deposit action is repeated")
    public void theDepositActionIsRepeated() throws Exception {
        Operations depositOperation = new ro.uvt.dp.AccountPack.Commander.depose(commandAcc, currentBalance - initialBalance);
        commandManager.executeOp(depositOperation);
    }

    @And("{int} RON is retrieved from the account")
    public void RONIsRetrievedFromTheAccount(int retrievalAmount) throws Exception {
        Operations retrieveOperation = new ro.uvt.dp.AccountPack.Commander.retrieve(commandAcc, retrievalAmount);
        commandManager.executeOp(retrieveOperation);
        currentBalance -= retrievalAmount;
    }

    @Then("the account balance should be {double} RON")
    public void theAccountBalanceShouldBeRON(double expectedBalance) {
        assertEquals(expectedBalance, commandAcc.getTotalAmount(), 0.0001);
    }

    @When("the actions are undone")
    public void theActionsAreUndone() throws Exception {
        commandManager.undo();
    }

    @When("the actions are undone and redone")
    public void theActionsAreUndoneAndRedone() throws Exception {
        commandManager.undo();
        commandManager.redo();
    }

    @When("{int} RON is deposited into the account again")
    public void ronIsDepositedIntoTheAccountAgain(int depositAmount) throws Exception {
        Operations depositOperation = new ro.uvt.dp.AccountPack.Commander.depose(commandAcc, depositAmount);
        commandManager.executeOp(depositOperation);
        currentBalance += depositAmount;
    }

    @And("{int} RON is retrieved from the account again")
    public void ronIsRetrievedFromTheAccountAgain(int retrievalAmount) throws Exception {
        Operations retrieveOperation = new ro.uvt.dp.AccountPack.Commander.retrieve(commandAcc, retrievalAmount);
        commandManager.executeOp(retrieveOperation);
        currentBalance -= retrievalAmount;
    }

    @When("life insurance is added to the account")
    public void lifeInsuranceIsAddedToTheAccount() throws Exception {
        AbstractFactory RONFactory = new AccountRON.RONFactory();
        this.decoratedAcc = RONFactory.createRONAccount("insurance1", 100);
        assertNotNull(decoratedAcc);
        decoratedAcc = new LifeInsurance(decoratedAcc);
    }

    @When("the economy feature is added to the account")
    public void theEconomyFeatureIsAddedToTheAccount() throws Exception {
        AbstractFactory EURFactory = new AccountEUR.EURFactory();
        this.decoratedAcc = EURFactory.createEURAccount("economy1", 100);
        decoratedAcc = new EconomyAccount(decoratedAcc);
    }

    @When("the child account feature is added to the account")
    public void theChildAccountFeatureIsAddedToTheAccount() throws Exception {
        AbstractFactory EURFactory = new AccountEUR.EURFactory();
        this.decoratedAcc = EURFactory.createEURAccount("child1", 100);
        decoratedAcc = new ChildAccount(decoratedAcc);
    }

    @When("both the economy feature and life insurance are added to the account")
    public void bothTheEconomyFeatureAndLifeInsuranceAreAddedToTheAccount() throws Exception {
        AbstractFactory EURFactory = new AccountEUR.EURFactory();
        this.decoratedAcc = EURFactory.createEURAccount("both1", 100);
        decoratedAcc = new LifeInsurance(new EconomyAccount(decoratedAcc));

    }

    @Then("the account should be represented as {string}")
    public void theAccountShouldBeRepresentedAs(String expectedRepresentation) throws Exception {
        String actualRepresentation = decoratedAcc.toString().replace("\n\t", System.lineSeparator());
        assertEquals(expectedRepresentation, actualRepresentation);
    }


    @And("an EUR account with number {string} and an initial balance of {int} exists")
    public void anEURAccountWithNumberAndAnInitialBalanceOfExists(String accountNumber, int sum) throws Exception {
        AbstractFactory EURFactory = new AccountEUR.EURFactory();
        Account account = new EconomyAccount(EURFactory.createRONAccount(accountNumber, sum));
        assertNotNull(account);
        account = new EconomyAccount(account);
    }


    AbstractFactory factory;
    Account createdAccount;

    @Given("an EUR account factory")
    public void anEURAccountFactory() {
        factory = new AccountEUR.EURFactory();
    }

    @Given("a RON account factory")
    public void aRONAccountFactory() {
        factory = new AccountRON.RONFactory();
    }

    @When("an EUR account with number {string} and an initial balance of {int} is created using the factory")
    public void anEURAccountWithNumberAndAnInitialBalanceIsCreatedUsingTheFactory(String accountNumber, int initialBalance) throws Exception {
        createdAccount = factory.createEURAccount(accountNumber, initialBalance);
    }

    @When("a RON account with number {string} and an initial balance of {int} is created using the factory")
    public void aRONAccountWithNumberAndAnInitialBalanceIsCreatedUsingTheFactory(String accountNumber, int initialBalance) throws Exception {
        createdAccount = factory.createRONAccount(accountNumber, initialBalance);
    }

    @Then("the created account should be an instance of AccountEUR")
    public void theCreatedAccountShouldBeAnInstanceOfAccountEUR() {
        assertTrue(createdAccount instanceof AccountEUR);
    }

    @When("an attempt is made to transfer {int} EUR from the source account to the destination account")
    public void anAttemptIsMadeToTransferEURFromTheSourceAccountToTheDestinationAccount(int amount) {
        try {
            sourceAccount.transfer(destinationAccount, amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("an attempt is made to transfer {int} EUR from the destination account to the source account")
    public void anAttemptIsMadeToTransferEURFromTheDestinationAccountToTheSourceAccount(int amount) {
        try {
            sourceAccount.transfer(destinationAccount, amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("an attempt is made to deposit {int} EUR into the account")
    public void anAttemptIsMadeToDepositEURIntoTheAccount(int amount) {
        try {
            sourceAccount.depose(amount);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the created account should be an instance of AccountRON")
    public void theCreatedAccountShouldBeAnInstanceOfAccountRON() {
    }
}
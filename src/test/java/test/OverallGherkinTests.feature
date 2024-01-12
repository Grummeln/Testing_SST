Feature: Account Testing

    Scenario: Depositing money into an account
        Given the EUR account with number "EUR1234" exists with a balance of 1015 EUR
        When 500 EUR is deposited into the account
        Then the account balance should be 1530.15 EUR

    Scenario: Retrieving money from an account
        Given the EUR account with number "EUR13" exists with a balance of 1000 EUR
        When 500 EUR is retrieved from the account
        Then the account balance should be 505 EUR

    Scenario: Transferring money between accounts
        Given the source EUR account with number "EUR113" exists with a balance of 1000 EUR
        And the destination EUR account with number "EUR1037" exists with a balance of 200 EUR
        When 40 EUR is transferred from the source account to the destination account
        Then the destination account balance should be 161.6 EUR

    Scenario: Closing an account
        Given the EUR account with number "EUR127" exists with a balance of 1000 EUR
        When the account is set to closed
        Then an exception "Account closed." should be thrown when attempting any operation

    Scenario: Depositing into a closed account
        Given the closed EUR account with number "EUR23" exists with a balance of 1000 EUR
        When 200 EUR is attempted to be deposited into the account
        Then an exception "Account closed" should be thrown

    Scenario: Retrieving from a closed account
        Given the closed EUR account with number "EUR120" exists with a balance of 1000 EUR
        When an attempt is made to retrieve 200 EUR from the account
        Then an exception "Account closed" should be thrown

    Scenario: Transferring from a closed account
        Given the source closed EUR account with number "EUR1230" exists with a balance of 1000 EUR
        And the destination EUR account with number "EUR55400" exists with a balance of 200 EUR
        When an attempt is made to transfer 200 EUR from the source account to the destination account
        Then an exception "Account closed" should be thrown

    Scenario: Blocking an account
        Given the EUR account with number "EUR143" exists with a balance of 1000 EUR
        When the account is set to blocked
        Then an exception "Account blocked." should be thrown when attempting any operation

    Scenario: Depositing into a blocked account
        Given the blocked EUR account with number "EUR723" exists with a balance of 1000 EUR
        When 200 EUR is attempted to be deposited into the account
        Then an exception "Account is currently blocked." should be thrown

    Scenario: Retrieving from a blocked account
        Given the blocked EUR account with number "EUR823" exists with a balance of 1000 EUR
        When an attempt is made to retrieve 200 EUR from the account
        Then an exception "Account is currently blocked." should be thrown

    Scenario: Transferring from a blocked account
        Given the source blocked EUR account with number "EUR023" exists with a balance of 25 EUR
        And the destination EUR account with number "EUR554" exists with a balance of 200 EUR
        When an attempt is made to transfer 200 EUR from the source account to the destination account
        Then an exception "Account is currently blocked." should be thrown

    Scenario: Insufficient funds in a transfer
        Given the source EUR account with number "EUR137" exists with a balance of 1000 EUR
        And the destination EUR account with number "EUR321" exists with a balance of 20 EUR
        When an attempt is made to transfer 2000 EUR from the destination account to the source account
        Then an exception "Hello, you've tried to retrieve more than you currently own." should be thrown

    Scenario: Insufficient funds in a retrieval
        Given the EUR account with number "EUR33" exists with a balance of 1000 EUR
        When an attempt is made to retrieve 2000 EUR from the account
        Then an exception "Hello, you've tried to retrieve more than you currently own." should be thrown

    Scenario: Illegal fund deposit
        Given the EUR account with number "EUR103" exists with a balance of 1000 EUR
        When an attempt is made to deposit -2000 EUR into the account
        Then an exception "Hello, you've tried to add less than nothing somehow." should be thrown

    Scenario: Illegal fund retrieval
        Given the EUR account with number "EUR1275" exists with a balance of 1000 EUR
        When an attempt is made to retrieve -2000 EUR from the account
        Then an exception "Cannot retrieve less than 0" should be thrown

    Scenario: Illegal fund transfer
        Given the source EUR account with number "EUR1213" exists with a balance of 1000 EUR
        And the destination EUR account with number "EUR32231" exists with a balance of 200 EUR
        When an attempt is made to transfer -2000 EUR from the destination account to the source account
        Then an exception "Cannot transfer less than 0" should be thrown

    Scenario: Creating an account with a duplicate number
        Given an EUR account with number "EUR123" already exists with a balance of 1000 EUR
        When an attempt is made to create another EUR account with the same number "EUR123" and a balance of 1000 EUR
        Then an exception "Account with this number already exists." should be thrown

    Scenario: Creating an account with minimum values
        Given an attempt is made to create an EUR account with number "EUR1" and a balance of 0 EUR
        Then the account should be created with a balance of 0 EUR


    Scenario: Creating a Client with Builder
        Given a client with the name "John Doe" is being created using the ClientBuilder
        And the client's address is set to "123 Main St"
        And the client's age is set to 35
        And the client's phone number is set to 1234567890
        And the client's place of birth is set to "City"
        When the client is built
        Then the client should have the name "John Doe"
        And the client's address should be "123 Main St"
        And the client's age should be 35
        And the client's phone number should be 1234567890
        And the client's place of birth should be "City"

    Scenario: Adding an EUR Account to a Client
        Given a client named "Ionescu" is being created using the ClientBuilder
        And the client's age is set to 30
        And the client's phone number is set to 1234
        And the client's address is set to "Susan street"
        And an EUR account with the number "RON1234" and an initial balance of 400 is added to the client
        When the client is added to the BCR bank
        When the client is built
        Then the client should have an account with the number "RON1234"

    Scenario: Adding a RON Account to a Client
        Given a client named "John Doe" is being created using the ClientBuilder
        And the client's age is set to 30
        And the client's address is set to "123 Main St"
        And the client's phone number is set to 1234567890
        And the client's place of birth is set to "City"
        When the client is added to the BCR bank
        And a RON account with the number "RON002" and an initial balance of 1500 is added to the client
        When the client is built
        Then the client should have an account with the number "RON002"


    Scenario: Blocking and Unblocking an Account
        Given a bank named "BCR"
        And a RON account with number "commandAcc1" and an initial balance of 100 exists
        And a command manager is created
        When the account is blocked
        Then the account should be blocked
        When the account is unblocked
        Then the account should not be blocked
        And the actions should be undoable
        And the actions should be redoable

    Scenario: Depositing and Retrieving from an Account
        Given a bank named "BCR"
        And a RON account with number "commandAcc2" and an initial balance of 100 exists
        And a command manager is created
        When 30 RON is deposited into the account
        And the deposit action is repeated
        And 20 RON is retrieved from the account
        And 30 RON is deposited into the account again
        Then the account balance should be 175.1 RON
        And the actions should be undoable
        And the actions should be redoable

    Scenario: Undo and Redo with Different Sequences
        Given a bank named "BCR"
        And a RON account with number "commandAcc3" and an initial balance of 100 exists
        And a command manager is created
        When 30 RON is deposited into the account
        And 20 RON is retrieved from the account
        And the actions are undone
        Then the account balance should be 133.9 RON
        When 30 RON is deposited into the account again
        And 20 RON is retrieved from the account again
        And the actions are undone and redone
        Then the account balance should be 144.2 RON


    Scenario: Adding Life Insurance to an Account
        Given a bank named "BCR"
        And a RON account with number "insurance1" and an initial balance of 100 exists
        When life insurance is added to the account
        Then the account should be represented as "Account RON [Account: code=insurance1, amount=100.0] This account has insurance."

    Scenario: Adding Economy Feature to an Account
        Given a bank named "BCR"
        And an EUR account with number "economy1" and an initial balance of 100 exists
        When the economy feature is added to the account
        Then the account should be represented as "Account EUR [Account: code=economy1, amount=100.0] This is an economy account."

    Scenario: Adding Child Account Feature to an Account
        Given a bank named "BCR"
        And an EUR account with number "child1" and an initial balance of 100 exists
        When the child account feature is added to the account
        Then the account should be represented as "Account EUR [Account: code=child1, amount=100.0] This account is a child's account."

    Scenario: Adding Multiple Decorators to an Account
        Given a bank named "BCR"
        And an EUR account with number "both1" and an initial balance of 100 exists
        When both the economy feature and life insurance are added to the account
        Then the account should be represented as "Account EUR [Account: code=both1, amount=100.0] This is an economy account. This account has insurance."


    Scenario: Creating an EUR Account using EURFactory
        Given an EUR account factory
        When an EUR account with number "EUR123" and an initial balance of 1000 is created using the factory
        Then the created account should be an instance of AccountEUR

    Scenario: Creating a RON Account using RONFactory
        Given a RON account factory
        When a RON account with number "RON123" and an initial balance of 1000 is created using the factory
        Then the created account should be an instance of AccountRON


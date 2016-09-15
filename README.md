# XebiBank

This is a small exercise of a Bank.

## Instruction
Think of your personal bank account experience When in doubt, go for the simplest solution
 
### Requirements
Deposit and Withdrawal
Account statement (date, amount, balance)
Statement printing
 
### User Stories
US 1:
In order to save money
As a bank client
I want to make a deposit in my account
 
US 2:
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account
 
US 3:
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance)  of my operations

## Build
Build an executable jar:
```
mvn clean package
```
Launch it with:
```
java -jar path/to/bank-account/cli/target/cli-1.0-SNAPSHOT-jar-with-dependencies.jar
```
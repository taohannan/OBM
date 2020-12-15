package com.userfront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.AccountService;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;
import exceptions.BelowMinimumBalanceException;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static int nextAccountNumber = 11223145;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionService transactionService;

    public PrimaryAccount createPrimaryAccount() {
        //EFFECTS: return account number for PrimaryAccount
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountDao.save(primaryAccount);

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        //EFFECTS: return account number for SavingAccount
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDao.save(savingsAccount);
        System.out.println("test");

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }
    
    public void deposit(String accountType, double amount, Principal principal) {
        //OVERVIEW: Deposit for Primary Account and Saving Account
        //REQUIRES: accountType as  String type, amount as double type, principal as Principal object
        //MODIFIES: accountBalance = accountBalance + amount (by using mutator)
        //EFFECTS: accountBalance will be updated based on the amount

        User user = userService.findByUsername(principal.getName());

        //Deposit for Primary Account
        if (accountType.equalsIgnoreCase("Primary")) {
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
            transactionService.savePrimaryDepositTransaction(primaryTransaction);
            
        }
       // Deposit for Saving Account
        else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
            transactionService.saveSavingsDepositTransaction(savingsTransaction);
        }
    }
    
    public void withdraw(String accountType, double amount, Principal principal) throws BelowMinimumBalanceException{
        //OVERVIEW: Withdraw for Primary Account and Saving Account
        //REQUIRES: accountType as  String type, amount as double type, principal as Principal object
        //MODIFIES: accountBalance = accountBalance - amount (by using mutator)
        //EFFECTS: if amount > accountBalance, throws BelowMinimumBalanceException
        //          else, accountBalance will be updated based on the amount

        User user = userService.findByUsername(principal.getName());

            //Withdraw for Primary Account
            if (accountType.equalsIgnoreCase("Primary")) {
                PrimaryAccount primaryAccount = user.getPrimaryAccount();

                //formula to check the equality between accountBalance and amount
                int res =primaryAccount.getAccountBalance().compareTo(new BigDecimal(amount));

                if (res == -1){
                    //EXCEPTION: throw BelowMinimumBalanceException - if amount > accountBalance
                    throw new BelowMinimumBalanceException("Below Minimum balance exceed");}

                primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
                primaryAccountDao.save(primaryAccount);

                Date date = new Date();

                PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
                transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
            }
            //Withdraw for Saving Account
            else if (accountType.equalsIgnoreCase("Savings")) {
                SavingsAccount savingsAccount = user.getSavingsAccount();
                savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
                savingsAccountDao.save(savingsAccount);

                Date date = new Date();
                SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
                transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
            }
    }
    
    private int accountGen() {
        //EFFECTS: return nextAccountNumber increment value
        return ++nextAccountNumber;
    }

	

}

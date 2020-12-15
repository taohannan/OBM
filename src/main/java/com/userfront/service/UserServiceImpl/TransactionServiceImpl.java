package com.userfront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.PrimaryAccountDao;
import com.userfront.dao.PrimaryTransactionDao;
import com.userfront.dao.RecipientDao;
import com.userfront.dao.SavingsAccountDao;
import com.userfront.dao.SavingsTransactionDao;
import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.Recipient;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.service.TransactionService;
import com.userfront.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;
	
	@Autowired
	private SavingsTransactionDao savingsTransactionDao;
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private RecipientDao recipientDao;
	

	public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        //REQUIRES: username as  String type
        //EFFECTS: if PrimaryAccount found based on the username, return primaryTransactionList of the user
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        //REQUIRES: username as  String type
        //EFFECTS: if SavingsAccount found based on the username, return savingsTransactionList of the user
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

        return savingsTransactionList;
    }

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
        //REQUIRES: primaryTransaction as  PrimaryTransaction object type
        //MODIFIES: update Primary Account amount in database (deposit operation)
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        //REQUIRES: savingsTransaction as  SavingsTransaction object type
        //MODIFIES: update Saving Account amount in database (deposit operation)
        savingsTransactionDao.save(savingsTransaction);
    }
    
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
        //REQUIRES: primaryTransaction as  PrimaryTransaction object type
        //MODIFIES: update Primary Account amount in database (withdraw operation)
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
        //REQUIRES: savingsTransaction as  SavingsTransaction object type
        //MODIFIES: update Saving Account amount in database (withdraw operation)
        savingsTransactionDao.save(savingsTransaction);
    }
    
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        //OVERVIEW: transfer between PrimaryAccount and SavingsAccount
        //REQUIRES: transferFrom as String type, transferTo as String type, amount as String type,
        //          primaryAccount as PrimaryAccount object type, savingsAccount as SavingsAccount object type
        //MODIFIES: if transfer from PrimaryAccount to SavingsAccount,
        //              accountBalance PrimaryAccount = accountBalance PrimaryAccount - amount (by using mutator)
        //              accountBalance SavingsAccount = accountBalance SavingsAccount + amount (by using mutator)
        //          if transfer from SavingsAccount to PrimaryAccount
        //               accountBalance SavingsAccount = accountBalance SavingsAccount - amount (by using mutator)
        //               accountBalance PrimaryAccount = accountBalance PrimaryAccount + amount (by using mutator)

        //transfer from PrimaryAccount to SavingsAccount
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Account", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        }
        //transfer from SavingsAccount to PrimaryAccount
        else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        } else {
            //EXCEPTION: throw Exception
            throw new Exception("Invalid Transfer");
        }
    }
    
    public List<Recipient> findRecipientList(Principal principal) {
        //REQUIRES: principal as  Principal object type
        //EFFECTS: return recipientList from database based on the selected user
        String username = principal.getName();
        List<Recipient> recipientList = recipientDao.findAll().stream() 			//convert list to stream
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))	//filters the line, equals to username
                .collect(Collectors.toList());

        return recipientList;
    }

    public Recipient saveRecipient(Recipient recipient) {
        //REQUIRES: recipient as  Recipient object type
        //EFFECTS: return new Receipient details into database
        return recipientDao.save(recipient);
    }

    public Recipient findRecipientByName(String recipientName) {
        //REQUIRES: recipientName as  String type
        //EFFECTS: return a selected Receipient name from database
        return recipientDao.findByName(recipientName);
    }

    public void deleteRecipientByName(String recipientName) {
        //REQUIRES: recipientName as  String type
        //EFFECTS: return a selected Receipient details from database
        recipientDao.deleteByName(recipientName);
    }
    
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        //OVERVIEW: transfer from PrimaryAccount or SavingsAccount to someone else
        //REQUIRES: recipient as Recipient object type, accountType as String type, amount as String type,
        //          primaryAccount as PrimaryAccount object type, savingsAccount as SavingsAccount object type
        //MODIFIES: if transfer from PrimaryAccount or SavingsAccount to someone else,
        //              accountBalance PrimaryAccount or SavingsAccount= accountBalance PrimaryAccount or SavingsAccount - amount (by using mutator)


        //transfer from PrimaryAccount to someone else
	    if (accountType.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        }
        //transfer from SavingsAccount to someone else
	    else if (accountType.equalsIgnoreCase("Savings")) {
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }
}

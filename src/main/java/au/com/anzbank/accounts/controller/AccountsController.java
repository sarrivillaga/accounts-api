package au.com.anzbank.accounts.controller;

import au.com.anzbank.accounts.models.AccountModel;
import au.com.anzbank.accounts.models.TransactionModel;
import au.com.anzbank.accounts.models.jpa.Account;
import au.com.anzbank.accounts.repositories.AccountRepository;
import au.com.anzbank.accounts.repositories.TransactionRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static au.com.anzbank.accounts.utils.Constants.ACCOUNT_NUMBER;

@RestController
public class AccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Gson gson;

    /**
     * Returns list of accounts
     */
    @RequestMapping(value = "/api/v1/accounts", method = RequestMethod.GET)
    public String listAccounts() {
        return gson.toJson(accountRepository.findAll().stream()
                .map(account -> AccountModel.builder()
                        .accountNumber(account.getAccountNumber())
                        .accountName(account.getAccountName())
                        .accountType(account.getAccountTypeId().getAccountTypeDescription())
                        .balanceDate(account.getBalanceDate().toString())
                        .currency(account.getCurrency())
                        .openingAvailableBalance(account.getOpeningAvailableBalance())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * Returns an specific Account by accountNumber
     *
     * @param accountNumber
     * @return
     */
    @RequestMapping(value = "/api/v1/accounts/{accountNumber}", method = RequestMethod.GET)
    public String getAccount(@PathVariable(ACCOUNT_NUMBER) long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).get();
        return gson.toJson(AccountModel.builder()
                .accountNumber(account.getAccountNumber())
                .accountName(account.getAccountName())
                .accountType(account.getAccountTypeId().getAccountTypeDescription())
                .balanceDate(account.getBalanceDate().toString())
                .currency(account.getCurrency())
                .openingAvailableBalance(account.getOpeningAvailableBalance()));
    }

    /**
     * returns list of transactions by accountNumber
     *
     * @param accountNumber
     * @return
     */
    @RequestMapping(value = "/api/v1/accounts/{accountNumber}/transactions", method = RequestMethod.GET)
    public String getTransactions(@PathVariable(ACCOUNT_NUMBER) long accountNumber) {
        return gson.toJson(transactionRepository.findAllByAccount(accountNumber).stream()
                .map(transaction -> TransactionModel.builder()
                        .accountNumber(transaction.getAccountNumber().getAccountNumber())
                        .accountName(transaction.getAccountNumber().getAccountName())
                        .validDate(transaction.getValidDate())
                        .currency(transaction.getCurrency())
                        .debitAmount(transaction.getDebitAmount())
                        .creditAmount(transaction.getCreditAmount())
                        .type(transaction.getType())
                        .transactionNarrative(transaction.getTransactionNarrative())
                        .build())
                .collect(Collectors.toList()));
    }
}

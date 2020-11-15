package au.com.anzbank.accounts;

import au.com.anzbank.accounts.controller.AccountsController;
import au.com.anzbank.accounts.models.jpa.Account;
import au.com.anzbank.accounts.models.jpa.AccountType;
import au.com.anzbank.accounts.models.jpa.Transaction;
import au.com.anzbank.accounts.repositories.AccountRepository;
import au.com.anzbank.accounts.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AccountsController.class)
public class AccountsControllerTest {

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    TransactionRepository transactionRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testResponseOk() throws Exception {
        mvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testAccountListResponse() throws Exception {
        List<Account> accountModelList = new ArrayList<>();
        Account account = getAccount();
        accountModelList.add(account);
        given(accountRepository.findAll()).willReturn(accountModelList);

        mvc.perform(get("/api/v1/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountNumber").value(account.getAccountNumber()))
                .andExpect(jsonPath("$[0].accountName").value(account.getAccountName()))
                .andExpect(jsonPath("$[0].currency").value(account.getCurrency()));
    }

    @Test
    public void testAccountResponse() throws Exception {
        Optional<Account> account = Optional.of(getAccount());
        given(accountRepository.findByAccountNumber(12345L)).willReturn(account);

        mvc.perform(get("/api/v1/accounts/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber").value(account.get().getAccountNumber()))
                .andExpect(jsonPath("$.accountName").value(account.get().getAccountName()))
                .andExpect(jsonPath("$.currency").value(account.get().getCurrency()));
    }

    @Test
    public void testAccountTransactionsResponse() throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = getTransaction();
        transactionList.add(transaction);

        given(transactionRepository.findAllByAccount(12345L)).willReturn(transactionList);

        mvc.perform(get("/api/v1/accounts/12345/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    private Account getAccount() {
        return Account.builder()
                .accountNumber(12345L)
                .accountName("test")
                .accountTypeId(AccountType.builder()
                        .accountTypeId("001")
                        .accountTypeDescription("Savings")
                        .build()
                )
                .balanceDate(Calendar.getInstance().getTime())
                .currency("AUD")
                .openingAvailableBalance(new BigDecimal(100))
                .build();
    }

    private Transaction getTransaction() {
        return Transaction.builder()
                .transactionId(1)
                .accountNumber(getAccount())
                .creditAmount(new BigDecimal(0))
                .debitAmount(new BigDecimal(100))
                .currency("USD")
                .type("Debit")
                .validDate(Calendar.getInstance().getTime())
                .transactionNarrative("narrative")
                .build();
    }

}

package au.com.anzbank.accounts.repositories;

import au.com.anzbank.accounts.models.jpa.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static au.com.anzbank.accounts.utils.Constants.ACCOUNT_NUMBER;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAll();

    @Query("FROM Transaction a where a.accountNumber.accountNumber = :accountNumber")
    List<Transaction> findAllByAccount(@Param(ACCOUNT_NUMBER) long accountNumber);
}

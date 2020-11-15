package au.com.anzbank.accounts.repositories;

import au.com.anzbank.accounts.models.jpa.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();

    Optional<Account> findByAccountNumber(Long accountNumber);
}

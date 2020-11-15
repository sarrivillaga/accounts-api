package au.com.anzbank.accounts;

import au.com.anzbank.accounts.controller.AccountsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountsApplicationTests {

	@Autowired
	private AccountsController accountsController;

	@Test
	void contextLoads(){
		assertThat(accountsController).isNotNull();
	}

}

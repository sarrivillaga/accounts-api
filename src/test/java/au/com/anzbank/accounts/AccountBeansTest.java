package au.com.anzbank.accounts;


import au.com.anzbank.accounts.models.AccountModel;
import au.com.anzbank.accounts.models.TransactionModel;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class AccountBeansTest {

    @Test
    public void testAccountModel() {
        EqualsVerifier.forClass(AccountModel.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransactionModel() {
        EqualsVerifier.forClass(TransactionModel.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}

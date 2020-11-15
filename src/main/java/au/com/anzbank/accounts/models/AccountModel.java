package au.com.anzbank.accounts.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountModel {
    private final long accountNumber;
    private final String accountName;
    private final String accountType;
    private final String balanceDate;
    private final String currency;
    private final BigDecimal openingAvailableBalance;
}

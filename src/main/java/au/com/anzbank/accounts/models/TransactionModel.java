package au.com.anzbank.accounts.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class TransactionModel {
    private final long accountNumber;
    private final String accountName;
    private final Date validDate;
    private final String currency;
    private final BigDecimal debitAmount;
    private final BigDecimal creditAmount;
    private final String type;
    private final String transactionNarrative;
}

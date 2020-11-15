package au.com.anzbank.accounts.models.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

import static au.com.anzbank.accounts.utils.Constants.ACCOUNT_NUMBER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = ACCOUNT_NUMBER)
    private Account accountNumber;
    private Date validDate;
    private String currency;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private String type;
    private String transactionNarrative;
}

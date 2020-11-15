package au.com.anzbank.accounts.models.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

import static au.com.anzbank.accounts.utils.Constants.ACCOUNT_TYPE_ID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private Long accountNumber;
    private String accountName;
    @ManyToOne(targetEntity = AccountType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = ACCOUNT_TYPE_ID)
    private AccountType accountTypeId;
    private Date balanceDate;
    private String currency;
    private BigDecimal openingAvailableBalance;
}
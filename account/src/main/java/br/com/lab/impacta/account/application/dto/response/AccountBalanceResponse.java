package br.com.lab.impacta.account.application.dto.response;
import lombok.Data;

@Data
public class AccountBalanceResponse {
    private  Long accountId;

    private Double balance;

    public  AccountBalanceResponse() {}

    public  AccountBalanceResponse(Long accountId, Double balance) {
        this.setAccountId(accountId);
        this.setBalance(balance);
    }
}
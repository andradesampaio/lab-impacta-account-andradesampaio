package br.com.lab.impacta.account.api;

import br.com.lab.impacta.account.application.AccountApplication;
import br.com.lab.impacta.account.application.dto.response.AccountBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class BalanceController {

    @Autowired
    private AccountApplication accountApplication;

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<AccountBalanceResponse> balance(@PathVariable long accountId) {
        AccountBalanceResponse accountBalanceResponse = accountApplication.balance(accountId);

        return ResponseEntity.ok(accountBalanceResponse);
    }
}

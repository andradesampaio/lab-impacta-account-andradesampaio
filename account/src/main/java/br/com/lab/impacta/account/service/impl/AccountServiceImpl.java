package br.com.lab.impacta.account.service.impl;

import br.com.lab.impacta.account.handlers.exception.AccountDontExistsException;
import br.com.lab.impacta.account.handlers.exception.AccountWithoutBalanceException;
import br.com.lab.impacta.account.model.Account;
import br.com.lab.impacta.account.repository.AccountRepository;
import br.com.lab.impacta.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Value("${lab.account.exceptions.account-dont-exists-message}")
    private String messageExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-dont-exists-description}")
    private String descriptionExceptionAccountDontExists;

    @Value("${lab.account.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.account.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Override
    public Account findAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountDontExistsException(messageExceptionAccountDontExists,
                descriptionExceptionAccountDontExists));
    }

    @Override
    public void debitAccount(Long accountId, Double valueOfDebit) {
        Optional<Account> account = accountRepository.findById(accountId);

        boolean debited = account.get().debit(valueOfDebit);

        if (!debited)
            throw new AccountWithoutBalanceException(messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        accountRepository.save(account.get());
    }
}

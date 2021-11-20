package br.com.lab.impacta.account.handlers.exception;

public class AccountWithoutBalanceException extends RuntimeException {

    private String description;

    public String getDescription() {
        return description;
    }

    public AccountWithoutBalanceException() {super();}

    public AccountWithoutBalanceException(String message, String description) {
        super(message);

        this.description = description;
    }
}

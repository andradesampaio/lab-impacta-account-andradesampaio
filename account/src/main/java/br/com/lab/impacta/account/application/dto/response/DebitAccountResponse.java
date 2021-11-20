package br.com.lab.impacta.account.application.dto.response;

import lombok.Data;

@Data
public class DebitAccountResponse {
    private boolean debited;

    public DebitAccountResponse() { }

    public  DebitAccountResponse(boolean debited){
        this.setDebited(debited);
    }
}

package ru.mart.ofd.model.ofdRuModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDtoResponse {
    private String login;
    private String authToken;
    private String expirationDateUtc;

    @Override
    public String toString() {
        return String.format("login: %s ; authToken: %s ; expirationDateUtc: %s", login, authToken, expirationDateUtc);
    }
}

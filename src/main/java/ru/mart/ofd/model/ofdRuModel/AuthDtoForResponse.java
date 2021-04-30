package ru.mart.ofd.model.ofdRuModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthDtoForResponse {
    private String authToken;
    private String expirationDateUtc;

    @Override
    public String toString() {
        return String.format("authToken: %s ; expirationDateUtc: %s", authToken, expirationDateUtc);
    }
}

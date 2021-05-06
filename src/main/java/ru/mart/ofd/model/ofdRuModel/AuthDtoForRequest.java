package ru.mart.ofd.model.ofdRuModel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class AuthDtoForRequest {
    private String login;
    private String password;


    @Override
    public String toString() {
        return String.format("login: %s ; password: %s", login, password);
    }
}

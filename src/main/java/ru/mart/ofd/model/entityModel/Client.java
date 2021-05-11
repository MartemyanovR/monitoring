package ru.mart.ofd.model.entityModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "passwrd")
    private  String password;
    @Column(name = "auth_token")
    private String authToken;
    @Column(name = "data_utc")
    private LocalDateTime dataUtc;



    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Set<Organization> organizations = new HashSet<>();

    @Override
    public String toString() {
        return String.format("Client [id=%d, login=%s, password=%s, authToken=%s," +
                        " dataUtc=" + dataUtc.truncatedTo(ChronoUnit.SECONDS) +"]" , id, password , login, authToken);
    }

}

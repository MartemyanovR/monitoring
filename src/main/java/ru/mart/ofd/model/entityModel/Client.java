package ru.mart.ofd.model.entityModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
    private Instant dataUtc;



    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Set<Organization> organizations = new HashSet<>();

    @Override
    public String toString() {
        String dataUtcOutput = null;
        if(dataUtc != null) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                            .withLocale(Locale.getDefault())
                            .withZone(ZoneId.systemDefault());
            dataUtcOutput = formatter.format(dataUtc);
        }
        return String.format("Client [id=%d, login=%s, password=%s, authToken=%s, dataUtc=" + dataUtcOutput +"]" ,
                id, password , login, authToken);
    }

}

package ru.mart.ofd.model.entityModel;

import lombok.Data;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "data_utc")
    private Instant dataUtc;
    @Column(name = "auth_token")
    private String authToken;
    @Column(name = "passwrd")
    private  String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Set<Organization> organizations = new HashSet<>();

    @Override
    public String toString() {
        return String.format("Client [id=%d, login=%s, authToken=%s, dataUtc=%t]" +
                id, login, authToken, dataUtc);
    }

}

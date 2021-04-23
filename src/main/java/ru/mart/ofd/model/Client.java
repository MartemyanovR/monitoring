package ru.mart.ofd.model;

import lombok.Data;


import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String login;
    @Column
    private Instant dataUtc;
    @Column
    private String authToken;
    @Column
    private  String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Organizaton> organizatons;

    @Override
    public String toString() {
        return String.format("Client [id=%d, login=%s, authToken=%s, dataUtc=%t]" +
                id, login, authToken, dataUtc);
    }

}

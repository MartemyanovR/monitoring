package ru.mart.ofd.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organizaton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "inn")
    private String inn;
    @Column(name = "name")
    private String name;
    @Column(name="legalAddress")
    private String address;
    @Column(name="client_id")
    private Long clientId;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Kkt> kkts;

    @Override
    public String toString() {
        return String.format("Organization +" +
                "[id=%d, inn=%s, name=%s address=%s, clientId=%s]" +
                                        id, inn, name,address,clientId);
    }


}

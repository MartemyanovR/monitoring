package ru.mart.ofd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "inn")
    private String inn;
    @Column(name = "name_org")
    private String name_org;
    @Column(name="legal_address")
    private String address;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="client_id")
//    private Client client;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private Set<Kkt> kkts = new HashSet<Kkt>();

    @Override
    public String toString() {
        return String.format("Organization +" +
                "[id=%d, inn=%s, name=%s address=%s, client=%s]" +
                                        id, inn, name_org,address);
    }


}

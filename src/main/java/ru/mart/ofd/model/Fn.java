package ru.mart.ofd.model;

import javax.persistence.*;

@Entity
@Table(name = "fn")
public class Fn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fn_number")
    private String number;
    @Column(name = "model")
    private String model;
    @Column(name = "validity")
    private Byte validity;

    @ManyToOne
    @JoinColumn(name = "kkt_id")
    private Kkt kkt;

}

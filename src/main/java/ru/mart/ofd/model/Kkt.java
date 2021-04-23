package ru.mart.ofd.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "kkt")
public class Kkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KktRegId")
    private String id;
    @Column(name = "SerialNumber")
    private String serialNumber;
    @Column(name = "ActivationDate")
    private Instant activationDate;
    @Column(name = "FirstDocumentDate")
    private Instant firstDocDate;
    @Column(name = "ContractStartDate")
    private Instant contractStartDate;
    @Column(name = "ContractEndDate")
    private Instant contractEndDate;
    @Column(name = "LastDocOnKktDateTime")
    private Instant lastDocOnKktDateTime;
    @Column(name = "LastDocOnOfdDateTimeUtc")
    private Instant lastDocOnOfdDateTimeUtc;
    @Column(name = "FiscalAddress")
    private String fiscalAddress;
    @Column(name = "FiscalPlace")
    private String fiscalPlace;
    @Column(name = "KktModel")
    private String kktModel;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organizaton organization;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Fn> fns;







}

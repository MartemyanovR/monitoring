package ru.mart.ofd.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kkt")
public class Kkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "kkt_reg_id")
    private String ofdid;
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "activation_date")
    private LocalDate activation_date;
    @Column(name = "first_document_date")
    private Instant firstDocDate;
    @Column(name = "contract_start_date")
    private Instant contractStartDate;
    @Column(name = "contract_end_date")
    private Instant contractEndDate;
    @Column(name = "last_doc_on_kkt_date_time")
    private Instant lastDocOnKktDateTime;
    @Column(name = "last_doc_on_ofd_date_time_utc")
    private Instant lastDocOnOfdDateTimeUtc;
    @Column(name = "fiscal_address")
    private String fiscalAddress;
    @Column(name = "fiscal_place")
    private String fiscalPlace;
    @Column(name = "kkt_model")
    private String kktModel;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "kkt")
    private Set<Fn> fns = new HashSet<>();







}

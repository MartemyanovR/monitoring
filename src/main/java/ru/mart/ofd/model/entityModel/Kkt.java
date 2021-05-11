package ru.mart.ofd.model.entityModel;

import ru.mart.ofd.model.entityModel.Fn;
import ru.mart.ofd.model.entityModel.Organization;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime activation_date;
    @Column(name = "first_document_date")
    private LocalDateTime firstDocDate;
    @Column(name = "contract_start_date")
    private LocalDateTime contractStartDate;
    @Column(name = "contract_end_date")
    private LocalDateTime contractEndDate;
    @Column(name = "last_doc_on_kkt_date_time")
    private LocalDateTime lastDocOnKktDateTime;
    @Column(name = "last_doc_on_ofd_date_time_utc")
    private LocalDateTime lastDocOnOfdDateTimeUtc;
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

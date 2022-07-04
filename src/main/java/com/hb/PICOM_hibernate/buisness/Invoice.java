package com.hb.PICOM_hibernate.buisness;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "invoice_description", nullable = false)
    private String invoiceDescription;

    @Column(name = "invoice_created_at", nullable = false)
    private Instant invoiceCreatedAt;

    @Column(name = "signed_at", nullable = true)
    private Instant signedAt;

    @Lob
    @Column(name = "signature", nullable = false)
    private String signature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ad", nullable = false)
    private Advertisment idAd;

    //CONSTRUCTOR//

    public Invoice() {
        this.invoiceCreatedAt = Instant.now();
    }

    public Invoice(String invoiceDescription, Instant signedAt, String signature, Advertisment idAd) {
        this();
        this.invoiceDescription = invoiceDescription;
        this.signedAt = signedAt;
        this.signature = signature;
        this.idAd = idAd;
    }


    //CONSTRUCTOR//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public Instant getInvoiceCreatedAt() {
        return invoiceCreatedAt;
    }

    public void setInvoiceCreatedAt(Instant invoiceCreatedAt) {
        this.invoiceCreatedAt = invoiceCreatedAt;
    }

    public Instant getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(Instant signedAt) {
        this.signedAt = signedAt;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Advertisment getIdAd() {
        return idAd;
    }

    public void setIdAd(Advertisment idAd) {
        this.idAd = idAd;
    }

}
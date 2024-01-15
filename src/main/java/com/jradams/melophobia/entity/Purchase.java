package com.jradams.melophobia.entity;

import com.jradams.melophobia.entity.backing.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Purchase {

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @OneToOne
    @JoinColumn(name = "issue_id")
    @NotNull(message = "Issue is mandatory")
    private Issue issue;

    @Column(precision = 7, scale = 2)
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    private Currency currencyCode;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}

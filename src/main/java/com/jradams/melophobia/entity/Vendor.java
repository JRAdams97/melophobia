package com.jradams.melophobia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vendorId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Postcode is mandatory")
    @Size(max = 10, message = "Postcode is too long")
    private String postcode;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Size(max = 255, message = "Description is too long")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vendor")
    private Set<Purchase> purchases;
}

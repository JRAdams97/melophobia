package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long countryId;

    @NotBlank(message = "Country name is mandatory")
    @Size(max = 42, message = "Country name is too long")
    private String countryName;

    @Column(name = "alpha_2_code")
    @NotBlank
    @Pattern(regexp = "[A-Z]{2}", message = "Alpha 2 code must consist of two uppercase letters")
    private String alpha2Code;

    @Enumerated(EnumType.STRING)
    private Continent countryContinent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private Set<Region> regions;
}

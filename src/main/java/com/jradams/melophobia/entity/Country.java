package com.jradams.melophobia.entity;

import com.jradams.melophobia.entity.backing.Continent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @NotBlank(message = "Country name is mandatory")
    @Size(max = 42, message = "Country name is too long")
    private String countryName;

    @Column(name = "alpha_2_code")
    @NotBlank(message = "Alpha2 Code is mandatory")
    @Pattern(regexp = "[A-Z]{2}", message = "Alpha 2 code must consist of two uppercase letters")
    private String alpha2Code;

    private Continent countryContinent;
}

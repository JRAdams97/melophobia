package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long regionId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "region_abbr")
    @Size(max = 30, message = "Region abbreviation is too long")
    private String regionAbbreviation;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}

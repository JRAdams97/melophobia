package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Series {

    @Id
    @Column(name = "series_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Origin year is mandatory")
    private Integer originYear;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}

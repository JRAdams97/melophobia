package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mediaId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "media_abbr")
    @Size(max = 20, message = "Media abbreviation is too long")
    private String mediaAbbreviation;

    @Size(max = 50, message = "Media classification is too long")
    private String classification;

    @NotNull(message = "Origin year is mandatory")
    private Integer originYear;
}

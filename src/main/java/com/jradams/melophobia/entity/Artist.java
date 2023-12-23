package com.jradams.melophobia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formation date format: '####-##-##'")
    private String formationDate;

    @ManyToOne
    @JoinColumn(name = "formation_location_id")
    private Location formationLocation;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Disband date format: '####-##-##'")
    private String disbandDate;

    private Boolean favourite;

    @Enumerated(EnumType.STRING)
    private ArtistType artistType;

    @NotBlank
    @Pattern(regexp = "\\d{4} \\d{4} \\d{4} \\d{4}", message = "ISNI format: '#### #### #### ####'")
    private String isni;

    @ManyToMany
    @JoinTable(name = "artist_genres", joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
}

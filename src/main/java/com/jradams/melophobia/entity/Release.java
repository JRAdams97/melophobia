package com.jradams.melophobia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long releaseId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Release date format: '####-##-##'")
    private String releaseDate;

    @ManyToMany
    @JoinTable(name = "release_artists", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @ToString.Exclude
    private Set<Artist> artists;

    @ManyToMany
    @JoinTable(name = "release_genres", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private Set<Genre> genres;

    @ManyToMany
    @JoinTable(name = "release_languages", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages;

    @ManyToMany
    @JoinTable(name = "release_types", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "release_type_id"))
    private Set<ReleaseType> releaseTypes;

    private Boolean favourite;
    private Double rymRating;
    private Integer aotyRank;
    private Integer beaRank;
    private String christgauRating;
    private Double scaruffiRating;
    private Double pitchforkRating;
    private Integer metacritic;

    @ManyToMany
    @JoinTable(name = "release_producers", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "producer_id"))
    private Set<Producer> producers;

    @ManyToMany
    @JoinTable(name = "release_series", joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "series_id"))
    private Set<Series> series;

    @NotNull(message = "Officiality is mandatory")
    private Boolean isOfficial;
}

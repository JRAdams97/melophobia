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
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @ManyToMany
    @JoinTable(name = "track_composers", joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "composer_id"))
    private Set<Composer> composers;

    @ManyToOne
    @JoinColumn(name = "first_release_id")
    private Release firstRelease;

    private Boolean favourite;
    private TrackType trackType;

    @ManyToMany
    @JoinTable(name = "track_isrc", joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "isrc_id"))
    private Set<Isrc> isrcs;

    @ManyToMany
    @JoinTable(name = "track_iswc", joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "iswc_id"))
    private Set<Iswc> iswcs;

    @ManyToMany
    @JoinTable(name = "track_original_artists", joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> originalArtists;

    @ManyToMany
    @JoinTable(name = "track_recorded_artists", joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> recordedArtists;

}

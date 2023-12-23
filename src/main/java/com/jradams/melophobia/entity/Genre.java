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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genreId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Origin year is mandatory")
    private Integer originYear;

    private Boolean favourite;

    @ManyToMany
    @JoinTable(name = "genre_hierarchy", joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_genre_id"))
    private Set<Genre> parentGenres;

    @ManyToMany(mappedBy = "genres")
    private Set<Artist> artists;
}

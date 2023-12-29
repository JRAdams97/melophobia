package com.jradams.melophobia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Issue {

    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @ManyToOne
    @JoinColumn(name = "label_id")
    private Label label;

    @ManyToOne
    @JoinColumn(name = "release_id")
    private Release release;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Release date format: '####-##-##'")
    private String releaseDate;

    @NotBlank(message = "Catalogue ID is mandatory")
    private String catalogueId;

    @ManyToMany
    @JoinTable(name = "issue_countries", joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

    @NotBlank(message = "Edition is mandatory")
    private String edition;

    private Boolean isRerelease;
    private Boolean isOfficial;
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    private String matrixRunout;
    private String description;
}

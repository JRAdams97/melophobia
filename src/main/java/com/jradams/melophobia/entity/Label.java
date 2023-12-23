package com.jradams.melophobia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long labelId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formation date format: '####-##-##'")
    private String formationDate;

    @ManyToOne
    @JoinColumn(name = "formation_location_id")
    private Location formationLocation;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Closing date format: '####-##-##'")
    private String closingDate;

    private Boolean favourite;

    @Pattern(regexp = "LC \\d{5}", message = "Label code format: 'LC #####'")
    private String labelCode;
}

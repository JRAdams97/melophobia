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
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long producerId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Birth date format: '####-##-##'")
    private String birthDate;

    @ManyToOne
    @JoinColumn(name = "birth_location_id")
    private Location birthLocation;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Death date format: '####-##-##'")
    private String deathDate;
}

package com.jradams.melophobia.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Isrc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long isrcId;

    @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-\\d{2}-\\d{5}", message = "ISRC format: 'AA-AAA-##-#####'")
    private String isrc;
}

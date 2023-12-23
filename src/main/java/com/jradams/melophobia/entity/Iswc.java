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
public class Iswc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iswcId;

    @Pattern(regexp = "[A-Z]-\\d{3}.\\d{3}.\\d{3}-\\d", message = "ISWC format: 'A-###.###.###-#'")
    private String iswc;
}

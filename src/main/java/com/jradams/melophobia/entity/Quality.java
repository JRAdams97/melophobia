package com.jradams.melophobia.entity;

import lombok.Getter;

@Getter
public enum Quality {

    GOOD("Good"),
    MINT("Mint"),
    NEAR_MINT("Near Mint"),
    POOR("Poor"),
    VERY_GOOD("Very Good"),
    VERY_GOOD_PLUS("Very Good Plus");

    private final String grade;

    Quality(String grade) {
        this.grade = grade;
    }
}

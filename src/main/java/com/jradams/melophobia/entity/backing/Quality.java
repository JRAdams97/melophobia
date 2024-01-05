package com.jradams.melophobia.entity.backing;

import lombok.Getter;

@Getter
public enum Quality {

    POOR("Poor"),
    GOOD("Good"),
    VERY_GOOD("Very Good"),
    VERY_GOOD_PLUS("Very Good Plus"),
    NEAR_MINT("Near Mint"),
    MINT("Mint");

    private final String grade;

    Quality(String grade) {
        this.grade = grade;
    }
}

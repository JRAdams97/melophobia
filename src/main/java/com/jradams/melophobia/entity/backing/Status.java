package com.jradams.melophobia.entity.backing;

import lombok.Getter;

@Getter
public enum Status {

    UNKNOWN("Unknown"),
    POOR("Poor"),
    OK("OK"),
    GOOD("Good"),
    VERY_GOOD("Very Good"),
    PERFECT("Perfect");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}

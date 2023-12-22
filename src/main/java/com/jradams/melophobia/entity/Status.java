package com.jradams.melophobia.entity;

import lombok.Getter;

@Getter
public enum Status {

    UNKNOWN(0),
    POOR(1),
    OK(2),
    GOOD(3),
    VERY_GOOD(4),
    PERFECT(5);

    private final int value;

    Status(int value) {
        this.value = value;
    }
}

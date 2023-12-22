package com.jradams.melophobia.entity;

import lombok.Getter;

@Getter
public enum TrackType {

    COVER("Cover"),
    ORIGINAL("Original");

    private final String type;

    TrackType(String type) {
        this.type = type;
    }
}

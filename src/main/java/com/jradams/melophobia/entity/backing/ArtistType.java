package com.jradams.melophobia.entity.backing;

import lombok.Getter;

@Getter
public enum ArtistType {

    BAND("Band"),
    CHOIR("Choir"),
    ORCHESTRA("Orchestra"),
    OTHER("Other"),
    PERSON("Person"),
    SUPERGROUP("Supergroup");

    private final String type;

    ArtistType(String type) {
        this.type = type;
    }
}

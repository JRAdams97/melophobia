package com.jradams.melophobia.entity;

import lombok.Getter;

@Getter
public enum Continent {

    AFRICA("Africa"),
    ANTARCTICA("Antarctica"),
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    OCEANIA("Oceania"),
    SOUTH_AMERICA("South America");

    private final String title;

    Continent(String title) {
        this.title = title;
    }
}

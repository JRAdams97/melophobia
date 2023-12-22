package com.jradams.melophobia.entity;

import lombok.Getter;

@Getter
public enum ReleaseType {

    COMPILATION("Compilation"),
    EP("EP"),
    DEMO("Demo"),
    DJ_MIX("DJ Mix"),
    INTERVIEW("Interview"),
    LIVE("Live"),
    PROMO("Promo"),
    REMIX("Remix"),
    SAMPLER("Sampler"),
    SOUNDTRACK("Soundtrack"),
    SINGLE("Single"),
    SPLIT("Split"),
    STUDIO("Studio"),
    TRIBUTE("Tribute"),
    VIDEO("Video");

    private final String type;

    ReleaseType(String type) {
        this.type = type;
    }
}

package com.jradams.melophobia.entity.backing;

import lombok.Getter;

@Getter
public enum LanguageName {

    AFRIKAANS("Afrikaans"),
    ALBANIAN("Albanian"),
    ARABIC("Arabic"),
    ARMENIAN("Armenian"),
    BOSNIAN("Bosnian"),
    BULGARIAN("Bulgarian"),
    CHINESE_SIMPLIFIED("Chinese (Simplified)"),
    CHINESE_TRADITIONAL("Chinese (Traditional)"),
    CROATIAN("Croatian"),
    CZECH("Czech"),
    DANISH("Danish"),
    DUTCH("Dutch"),
    ENGLISH("English"),
    ESPERANTO("Esperanto"),
    ESTONIAN("Estonian"),
    FILIPINO("Filipino"),
    FINNISH("Finnish"),
    FRENCH("French"),
    GAELIC("Gaelic"),
    GEORGIAN("Georgian"),
    GERMAN("German"),
    GREEK("Greek"),
    HEBREW("Hebrew"),
    HUNGARIAN("Hungarian"),
    ICELANDIC("Icelandic"),
    INDONESIAN("Indonesian"),
    IRISH("Irish"),
    ITALIAN("Italian"),
    JAPANESE("Japanese"),
    KOREAN("Korean"),
    LAO("Lao"),
    LATIN("Latin"),
    LATVIAN("Latvian"),
    LITHUANIAN("Lithuanian"),
    LUXEMBOURGISH("Luxembourgish"),
    MACEDONIAN("Macedonian"),
    MALAY("Malay"),
    MALTESE("Maltese"),
    MONGOLIAN("Mongolian"),
    NEPALI("Nepali"),
    NORWEGIAN("Norwegian"),
    PERSIAN("Persian"),
    POLISH("Polish"),
    PORTUGUESE("Portuguese"),
    ROMANIAN("Romanian"),
    RUSSIAN("Russian"),
    SERBIAN("Serbian"),
    SLOVAK("Slovak"),
    SLOVENIAN("Slovenian"),
    SOMALI("Somali"),
    SPANISH("Spanish"),
    SUDANESE("Sudanese"),
    SWAHILI("Swahili"),
    SWEDISH("Swedish"),
    TAGALOG("Tagalog"),
    THAI("Thai"),
    TURKISH("Turkish"),
    UKRANIAN("Ukranian"),
    VIETNAMESE("Vietnamese"),
    WELSH("Welsh"),
    YIDDISH("Yiddish"),
    ZULU("Zulu");

    private final String name;

    LanguageName(String name) {
        this.name = name;
    }
}

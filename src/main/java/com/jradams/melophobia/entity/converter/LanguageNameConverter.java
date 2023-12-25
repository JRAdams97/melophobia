package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.backing.LanguageName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class LanguageNameConverter implements AttributeConverter<LanguageName, String> {

    @Override
    public String convertToDatabaseColumn(LanguageName language) {
        if (language == null) {
            return null;
        }

        return language.getName();
    }

    @Override
    public LanguageName convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }

        return Stream.of(LanguageName.values())
                .filter(v -> v.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

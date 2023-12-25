package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.backing.Continent;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ContinentConverter implements AttributeConverter<Continent, String> {

    @Override
    public String convertToDatabaseColumn(Continent continent) {
        if (continent == null) {
            return null;
        }

        return continent.getTitle();
    }

    @Override
    public Continent convertToEntityAttribute(String title) {
        if (title == null) {
            return null;
        }

        return Stream.of(Continent.values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

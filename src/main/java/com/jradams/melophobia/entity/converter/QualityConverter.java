package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.Quality;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class QualityConverter implements AttributeConverter<Quality, String> {

    @Override
    public String convertToDatabaseColumn(Quality quality) {
        if (quality == null) {
            return null;
        }
        return quality.getGrade();
    }

    @Override
    public Quality convertToEntityAttribute(String grade) {
        if (grade == null) {
            return null;
        }

        return Stream.of(Quality.values())
                .filter(c -> c.getGrade().equals(grade))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.ReleaseType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ReleaseTypeConverter implements AttributeConverter<ReleaseType, String> {

    @Override
    public String convertToDatabaseColumn(ReleaseType releaseType) {
        if (releaseType == null) {
            return null;
        }
        return releaseType.getType();
    }

    @Override
    public ReleaseType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(ReleaseType.values())
                .filter(c -> c.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

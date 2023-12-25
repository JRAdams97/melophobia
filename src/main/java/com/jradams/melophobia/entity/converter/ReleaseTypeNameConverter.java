package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.backing.ReleaseTypeName;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ReleaseTypeNameConverter implements AttributeConverter<ReleaseTypeName, String> {

    @Override
    public String convertToDatabaseColumn(ReleaseTypeName releaseTypeName) {
        if (releaseTypeName == null) {
            return null;
        }

        return releaseTypeName.getType();
    }

    @Override
    public ReleaseTypeName convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(ReleaseTypeName.values())
                .filter(v -> v.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

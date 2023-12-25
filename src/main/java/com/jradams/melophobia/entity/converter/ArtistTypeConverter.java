package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.backing.ArtistType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ArtistTypeConverter implements AttributeConverter<ArtistType, String> {

    @Override
    public String convertToDatabaseColumn(ArtistType artistType) {
        if (artistType == null) {
            return null;
        }

        return artistType.getType();
    }

    @Override
    public ArtistType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(ArtistType.values())
                .filter(v -> v.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

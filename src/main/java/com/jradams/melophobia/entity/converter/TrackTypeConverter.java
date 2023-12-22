package com.jradams.melophobia.entity.converter;

import com.jradams.melophobia.entity.TrackType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TrackTypeConverter implements AttributeConverter<TrackType, String> {

    @Override
    public String convertToDatabaseColumn(TrackType trackType) {
        if (trackType == null) {
            return null;
        }
        return trackType.getType();
    }

    @Override
    public TrackType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(TrackType.values())
                .filter(c -> c.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

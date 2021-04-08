package org.fit.linevich_shchegoleva.converter_db;

import org.fit.linevich_shchegoleva.model.user_info.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender value) {
        if (value == null) {
            return null;
        }
        return value.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return Gender.findByCode(value);
    }
}

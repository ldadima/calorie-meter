package org.fit.linevich_shchegoleva.converter;

import org.fit.linevich_shchegoleva.model.user_info.Gender;
import org.springframework.core.convert.converter.Converter;

public class GenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Gender.findByCode(source);
    }
}
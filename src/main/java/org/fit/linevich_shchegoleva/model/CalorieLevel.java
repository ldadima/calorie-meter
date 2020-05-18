package org.fit.linevich_shchegoleva.model;

public enum CalorieLevel {
    LOW("Низкая"),
    NORMAl("Умеренная"),
    HIGH("Высокая");

    private final String code;

    CalorieLevel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    // static CalorieLevel findByCode(String code){
    //     for(CalorieLevel one: values()){
    //         if(code.equals(one.code))
    //             return one;
    //     }
    //     throw new IllegalArgumentException(String.format("Error code for calorie level: %s", code));
    // }
}

package org.fit.linevich_shchegoleva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFood {
    @NotNull
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Integer weight;
    @NotNull
    private Integer calories;
}

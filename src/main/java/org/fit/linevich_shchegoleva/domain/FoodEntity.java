package org.fit.linevich_shchegoleva.domain;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "food", schema = "public", catalog = "calori_meter")
public class FoodEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "weight")
    private Integer weight;

    @Basic
    @Column(name = "calories")
    private Integer calories;

}

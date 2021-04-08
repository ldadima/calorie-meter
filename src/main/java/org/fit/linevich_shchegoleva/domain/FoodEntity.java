package org.fit.linevich_shchegoleva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fit.linevich_shchegoleva.model.food.CalorieLevel;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food", schema = "public", catalog = "calori_meter")
public class FoodEntity {
    @Id
    @GeneratedValue(generator = "food_gen")
    @SequenceGenerator(name = "food_gen", sequenceName = "food_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "calories")
    private Integer calories;

    @Transient
    private CalorieLevel level;

    @PostLoad
    void post(){
        if(calories < 80) {
            level = CalorieLevel.LOW;
        }
        else {
            if (calories <= 200) {
                level = CalorieLevel.NORMAl;
            } else {
                level = CalorieLevel.HIGH;
            }
        }
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food", orphanRemoval = true)
    @ToString.Exclude
    private Collection<UserFoodsEntity> userFoodsEntities;
}

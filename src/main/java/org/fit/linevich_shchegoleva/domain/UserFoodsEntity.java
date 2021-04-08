package org.fit.linevich_shchegoleva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_foods", schema = "public", catalog = "calori_meter")
public class UserFoodsEntity {
    @EmbeddedId
    private UserFoodsPK foodsPK;

    @MapsId("userLogin")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_login", referencedColumnName = "login", nullable = false, insertable = false, updatable = false)
    private UserEntity userLogin;
    @MapsId("food")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private FoodEntity food;
    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;
}

package org.fit.linevich_shchegoleva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_foods", schema = "public", catalog = "calori_meter")
@IdClass(UserFoodsPK.class)
public class UserFoodsEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_login", referencedColumnName = "login", nullable = false, insertable = false, updatable = false)
    private UserEntity userLogin;
    @Id
    @ManyToOne
    @JoinColumn(name = "food", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private FoodEntity food;
    @Basic
    @Column(name = "weight", nullable = false)
    private Integer weight;
}

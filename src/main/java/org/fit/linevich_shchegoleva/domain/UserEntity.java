package org.fit.linevich_shchegoleva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.fit.linevich_shchegoleva.converter_db.GenderConverter;
import org.fit.linevich_shchegoleva.model.user_info.Gender;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "public", catalog = "calori_meter")
public class UserEntity {
    @Id
    @Column(name = "login", nullable = false)
    private String login;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Transient
    private Integer age;
    @Basic
    @Column(name = "height")
    private Integer height;
    @Basic
    @Column(name = "weight")
    private Integer weight;
    @Basic
    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @PostLoad
    void post(){
        Period period = Period.between(LocalDate.now(), LocalDate.parse(birthday.toString()));
        this.age = period.getYears();
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userLogin", orphanRemoval = true)
    @ToString.Exclude
    private Collection<UserFoodsEntity> userFoodsEntities;

    public void addFood(UserFoodsEntity foodsEntity){
        for(UserFoodsEntity one: userFoodsEntities){
            if(one.getFood().getId().equals(foodsEntity.getFood().getId())){
                one.setWeight(one.getWeight() + foodsEntity.getWeight());
                return;
            }
        }
        userFoodsEntities.add(foodsEntity);
    }

    public void deleteFood(int food){
        userFoodsEntities.removeIf(foodsEntity -> foodsEntity.getFoodsPK().getFood()==food);
    }

    public void deleteAllFood(){
        userFoodsEntities.clear();
    }
}

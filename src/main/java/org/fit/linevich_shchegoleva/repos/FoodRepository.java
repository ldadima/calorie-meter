package org.fit.linevich_shchegoleva.repos;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodEntity, Integer> {
}

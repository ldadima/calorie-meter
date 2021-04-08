package org.fit.linevich_shchegoleva.repos;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface FoodRepository extends PagingAndSortingRepository<FoodEntity, Integer> {
    @Override
    Page<FoodEntity> findAll(Pageable pageable);

    Page<FoodEntity> findAllByNameContainingIgnoreCase(String subName, Pageable pageable);

    Optional<FoodEntity> findByNameAndCalories(String name, int calories);
}

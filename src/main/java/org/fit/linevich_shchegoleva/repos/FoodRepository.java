package org.fit.linevich_shchegoleva.repos;

import org.fit.linevich_shchegoleva.domain.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<FoodEntity, Integer> {
    @Override
    Page<FoodEntity> findAll(Pageable pageable);
}

package org.fit.linevich_shchegoleva.repos;

import org.fit.linevich_shchegoleva.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}

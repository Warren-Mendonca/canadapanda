package com.war.canadapanda.core.repositories;

import com.war.canadapanda.core.model.UserInfoModel;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface UserInfoRepository extends CrudRepository<UserInfoModel, String> {

    Optional<UserInfoModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

package com.test.lambdacrud.repository;

import com.test.lambdacrud.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    boolean existsByName(String name);

}

package com.mapr.springframework.data.maprdb;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComplexUserRepository  extends CrudRepository<User, String> {
    List<User> findByName(String name);

    List<User> findByNameNot(String name);

    List<User> findByNameLike(String name);

    List<User> findByNameNotLike(String name);

    List<User> findByNameIn(List<String> names);

    List<User> findByNameNotIn(List<String> names);

    List<User> findByNameExists();

    List<User> findByEnabledTrue();

    List<User> findByEnabledFalse();

    List<User> findByAgeLessThan(int age);

    List<User> findByAgeLessThanEqual(int age);

    List<User> findByAgeGreaterThan(int age);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByNameContaining(String name);
}
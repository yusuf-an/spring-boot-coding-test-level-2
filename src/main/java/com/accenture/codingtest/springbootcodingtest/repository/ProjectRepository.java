package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, String> {

    List<Project> findAll();

    Optional<Project> findById(String id);


}
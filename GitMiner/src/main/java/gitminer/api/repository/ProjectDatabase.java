package gitminer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gitminer.api.model.Project;

@Repository
public interface ProjectDatabase extends JpaRepository<Project, String> {}

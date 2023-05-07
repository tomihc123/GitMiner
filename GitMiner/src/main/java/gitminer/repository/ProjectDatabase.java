package gitminer.repository;

import gitminer.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectDatabase extends JpaRepository<Project, String> {}

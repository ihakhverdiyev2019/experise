package az.elixir.experise.repository;


import az.elixir.experise.model.DegreesAndCredentialsEntity;
import az.elixir.experise.model.ResearchAndWritingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchAndWritingsRepository extends JpaRepository<ResearchAndWritingsEntity, Integer> {

}
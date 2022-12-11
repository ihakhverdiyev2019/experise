package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.ResearchAndWritingsEntity;

@Repository
public interface ResearchAndWritingsRepository
    extends JpaRepository<ResearchAndWritingsEntity, Integer> {
  ResearchAndWritingsEntity findByIdAndLangId(int id, int langId);

  List<ResearchAndWritingsEntity> findAllByLangId(int langId);
}

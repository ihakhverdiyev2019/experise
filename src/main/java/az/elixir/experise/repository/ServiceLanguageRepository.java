package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.ServiceLanguageEntity;

@Repository
public interface ServiceLanguageRepository extends JpaRepository<ServiceLanguageEntity, Integer> {
  List<ServiceLanguageEntity> findAllByLanguageId(int languageId);
}

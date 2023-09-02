package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.LanguageEntity;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
  LanguageEntity findByLangCodeAndIsEnable(String langCode, boolean isEnable);

  LanguageEntity findByLangCode(String langCode);

  List<LanguageEntity> findAllByIsEnable(boolean isEnable);
}

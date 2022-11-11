package az.elixir.experise.repository;

import az.elixir.experise.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity,Integer> {
    LanguageEntity findByLangCodeAndIsEnable(String langCode, boolean isEnable);
}

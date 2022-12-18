package az.elixir.experise.repository;

import az.elixir.experise.model.ServiceLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceLanguageRepository extends JpaRepository<ServiceLanguageEntity,Integer> {
    List<ServiceLanguageEntity> findAllByLanguageId(int languageId);
}

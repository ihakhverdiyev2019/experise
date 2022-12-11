package az.elixir.experise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.FAQEntity;

@Repository
public interface FAQRepository extends JpaRepository<FAQEntity, Integer> {
  List<FAQEntity> findAllByLangId(int langId);
}

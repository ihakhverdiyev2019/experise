package az.elixir.experise.repository.seo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.seo.FAQSeoEntity;

@Repository
public interface FAQSeoRepository extends JpaRepository<FAQSeoEntity, Integer> {

  FAQSeoEntity findByLangId(int langId);

  FAQSeoEntity findByLangIdAndId(int langId, int id);
}

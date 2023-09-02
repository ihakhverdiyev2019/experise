package az.elixir.experise.repository.seo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.seo.AboutSeoEntity;

@Repository
public interface AboutSeoRepository extends JpaRepository<AboutSeoEntity, Integer> {

  AboutSeoEntity findByLangId(int langId);

  AboutSeoEntity findByLangIdAndId(int langId, int id);
}

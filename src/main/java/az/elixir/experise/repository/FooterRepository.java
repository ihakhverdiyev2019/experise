package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.FooterEntity;

@Repository
public interface FooterRepository extends JpaRepository<FooterEntity, Integer> {
  FooterEntity findByLangId(int langId);
}

package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.BannerEntity;

@Repository
public interface BannerRepository extends JpaRepository<BannerEntity, Integer> {
  BannerEntity findByLangId(int langId);
}

package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.PartnerEntity;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {}

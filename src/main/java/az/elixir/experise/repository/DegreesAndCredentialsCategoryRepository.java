package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.DegreesAndCredentialsCategoryEntity;

@Repository
public interface DegreesAndCredentialsCategoryRepository
    extends JpaRepository<DegreesAndCredentialsCategoryEntity, Integer> {}

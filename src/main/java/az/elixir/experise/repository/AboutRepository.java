package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.AboutEntity;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Integer> {}

package az.elixir.experise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.elixir.experise.model.PlaceEntity;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Integer> {}

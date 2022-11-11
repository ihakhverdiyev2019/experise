package az.elixir.experise.repository;


import az.elixir.experise.model.DegreesAndCredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreesAndCredentialsRepository extends JpaRepository<DegreesAndCredentialsEntity, Integer> {

    List<DegreesAndCredentialsEntity> findAllByCategory(String category);

}
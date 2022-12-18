package az.elixir.experise.repository;


import az.elixir.experise.model.DegreesAndCredentialsCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreesAndCredentialsCategoryRepository extends JpaRepository<DegreesAndCredentialsCategoryEntity, Integer> {

}
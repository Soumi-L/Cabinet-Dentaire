package in.bushansirgur.springbootthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer>{

}

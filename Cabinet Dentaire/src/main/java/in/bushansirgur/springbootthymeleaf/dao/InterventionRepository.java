package in.bushansirgur.springbootthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Intervention;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Integer>{

}

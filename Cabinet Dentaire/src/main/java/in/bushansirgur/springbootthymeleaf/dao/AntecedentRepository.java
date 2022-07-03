package in.bushansirgur.springbootthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Antecedent;

@Repository
public interface AntecedentRepository extends JpaRepository<Antecedent, Integer>{

}

package in.bushansirgur.springbootthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Rdv;

@Repository
public interface RdvRepository extends JpaRepository<Rdv, Integer>{

}

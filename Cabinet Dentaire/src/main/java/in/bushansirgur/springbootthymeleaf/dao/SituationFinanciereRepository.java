package in.bushansirgur.springbootthymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.SituationFinanciere;

@Repository
public interface SituationFinanciereRepository extends JpaRepository<SituationFinanciere, Integer>{

    @Query("Select sf.patient from SituationFinanciere sf where sf.patient like ?1")
    List<Patient> findByPatient(Patient p);
}

package in.bushansirgur.springbootthymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.bushansirgur.springbootthymeleaf.entity.Medecin;
import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.SituationFinanciere;

@Transactional
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer>{
     Medecin findByEmailPersonne(String email);
     Medecin findByPasswordPersonneUser(String pass);

     @Query("Select p from Patient p")
     List<Patient> findPatientRdv();

     @Modifying
     @Query("Update Medecin m set passwordPersonneUser=?1 where nomPersonne='soso' ")
     void UpdateMedecin(String string);
     
	Medecin findBynomPersonne(String string);

     @Query("Select sf from SituationFinanciere sf")
     List<SituationFinanciere> findPatientsSfs();

     @Query("Select sf from SituationFinanciere sf where patient like ?1")
     List<SituationFinanciere> findPatientSf(Patient pat);

     @Query("Select SUM(sf.mtPaye) from SituationFinanciere sf")
     Double findMtTotal();

}

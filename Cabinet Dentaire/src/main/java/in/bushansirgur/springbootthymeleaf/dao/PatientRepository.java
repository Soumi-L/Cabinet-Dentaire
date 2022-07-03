package in.bushansirgur.springbootthymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.Rdv;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
     @Query("Select p.rdvs from Patient p where p.numPersonne like ?1")
     List<Rdv> findByRdvs(Integer numPersonne);
     Patient findByEmailPersonne(String email);
     Patient findByPasswordPersonneUser(String pass);
     @Query("Select p.numPersonne from Patient p where p.numPersonne like ?1")
     Integer findId(Integer id);

     



}

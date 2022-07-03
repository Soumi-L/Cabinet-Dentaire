package in.bushansirgur.springbootthymeleaf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.bushansirgur.springbootthymeleaf.entity.Secretaire;
import in.bushansirgur.springbootthymeleaf.entity.Rdv;


@Transactional
@Repository
public interface SecretaireRepository extends JpaRepository<Secretaire, Integer>{
        Secretaire findByEmailPersonne(String email);
        Secretaire findByPasswordPersonneUser(String pass);


        @Modifying
        @Query("Update Secretaire s set passwordPersonneUser=?1 where nomPersonne='fatiha' ")
        void UpdateSecretaire(String string);

        Secretaire findBynomPersonne(String string);

}

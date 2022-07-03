package in.bushansirgur.springbootthymeleaf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.bushansirgur.springbootthymeleaf.entity.CertificatMed;

@Repository
public interface CertificatMedRepository extends JpaRepository<CertificatMed, Integer>{
}

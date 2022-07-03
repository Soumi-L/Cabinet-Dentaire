package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Certificat_meds")
public class CertificatMed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numCert", nullable = false)
    private Integer numCert;

    @Column(name = "dateRepDeb")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRepDeb;

    @Column(name = "nbr_jour_repos")
    private Integer nbrJourRepos;


    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numOrd")
    private Ordonnance ordonnance;

}

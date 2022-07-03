package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ordonnances")
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numOrd", nullable = false)
    private Integer numOrd;

    @Column(name = "dateOrd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOrd;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numOrd")
    List<Prescription> Prescription = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numCons")
    private Consultation consultation;

    /*@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "numCons")
    private Consultation consultation;

    @OneToOne(mappedBy = "ordonnanceCer")
    private CertificatMed certificatMed;

   public List<Antecedent> getAntecedents(Patient p) {
      return p.getAntecedents();
    }

    @OneToMany(mappedBy = "ordonnance")
    List<Prescription> Prescriptions = new ArrayList<>();*/

}

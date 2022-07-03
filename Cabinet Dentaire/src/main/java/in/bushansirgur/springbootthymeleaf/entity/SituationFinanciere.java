package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SituationFinancieres")
public class SituationFinanciere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numSf;
    
    @Column(name = "dateSf")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSf;
    private double total;
    private double mtPaye;
    private double restPaye;
    private String TypePayement;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numSf")
    List<Facture> factures = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "numPersonne")
    private Patient patient;
    
}

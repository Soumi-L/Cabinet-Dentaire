package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Consulations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numCons", nullable = false)
    private Integer numCons;

    @Column(name = "dateCons")
    private Instant dateCons;

    @Column(name = "motifCons", length = 30)
    private String motifCons;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
    @JoinColumn(name = "numCons")
    List<Rdv> rdvs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ConsId")
    List<Intervention> intervention = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numCons")
    List<Facture> facture = new ArrayList<>();



    /*@OneToOne(mappedBy = "consultation")
    private Ordonnance ordonnance;

    @OneToMany(mappedBy = "factureCons")
    List<Facture> factures = new ArrayList<>();*/

}

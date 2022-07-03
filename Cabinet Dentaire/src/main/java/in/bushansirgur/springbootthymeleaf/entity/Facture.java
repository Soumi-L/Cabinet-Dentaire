package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Factures")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numFacture;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numCons")
    private Consultation factureCons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numSf")
    private SituationFinanciere situationFinanciere;*/

}

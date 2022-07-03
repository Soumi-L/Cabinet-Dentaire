package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Prescriptions")
public class Prescription {

    @EmbeddedId
    private Prescription_id numPresc;

    @Column(name = "nbr_fois")
    private Integer nbrFois;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("numOrd")
    private Ordonnance ordonnance;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("numMed")
    private Medicament medicament;*/
}

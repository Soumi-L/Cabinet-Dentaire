package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numMed", nullable = false)
    private Integer numMed;

    @Column(name = "nomMed", length = 30)
    private String nomMed;

    @Column(name = "quantiteMed")
    private Integer quantiteMed;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "numMed")
    List<Prescription> Prescription = new ArrayList<>();


    /*@OneToMany(mappedBy = "medicament")
    List<Prescription> Prescription = new ArrayList<>();*/
}

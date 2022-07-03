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
@Table(name = "Antecedents")
public class Antecedent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_ant", nullable = false)
    private Integer numAntecedent;

    @Column(name = "categorie_ant", length = 30)
    private String categorieAnt;

    @Column(name = "description_ant", length = 30)
    private String descriptionAnt;

}

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
@Table(name = "Actes")
public class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numActe", nullable = false)
    private Integer numActe;

    @Column(name = "libelleActe", length = 30)
    private String libelleActe;

    @Column(name = "prixActe")
    private Double prixActe;

    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ActeId")
    List<Intervention> intervention = new ArrayList<>();

}

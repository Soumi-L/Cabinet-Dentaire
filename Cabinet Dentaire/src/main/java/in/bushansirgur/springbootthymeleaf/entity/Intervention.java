package in.bushansirgur.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Interventions")
public class Intervention {
    @EmbeddedId
    private Intervention_Id numInterv;

    private String descInterv;

    	/*@ManyToOne(fetch = FetchType.LAZY)
	    @MapsId("ActeId")
	    private Acte acte;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @MapsId("ConsId")
	    private Consultation consultationInter;*/

}

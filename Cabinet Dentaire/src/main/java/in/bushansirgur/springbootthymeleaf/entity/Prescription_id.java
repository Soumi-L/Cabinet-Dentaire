package in.bushansirgur.springbootthymeleaf.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Prescription_id implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Column(name = "numMed", nullable = false)
    private Integer numMed;

    @Column(name = "numOrd", nullable = false)
    private Integer numOrd;
}

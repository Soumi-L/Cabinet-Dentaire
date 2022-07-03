package in.bushansirgur.springbootthymeleaf.entity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class Intervention_Id implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

        private Integer ActeId;
        private Integer ConsId; 

}

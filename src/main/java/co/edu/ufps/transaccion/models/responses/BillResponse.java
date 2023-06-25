package co.edu.ufps.transaccion.models.responses;

import lombok.Data;

@Data
public class BillResponse {

    private Integer type;

    private Integer value;

    private String observation;
}

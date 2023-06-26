package co.edu.ufps.transaccion.models.responses;

import lombok.Data;

@Data
public class BillResponse {

    private Integer type;

    private Float value;

    private String observation;
}

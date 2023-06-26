package co.edu.ufps.transaccion.models.requests;

import lombok.Data;

@Data
public class BillRequest {
    
    private Integer type;

    private Float value;

    private String observation;
}

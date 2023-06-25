package co.edu.ufps.transaccion.models.requests;

import lombok.Data;

@Data
public class UserRequest {
    
    private String username;
    
    private String pass;
}

package co.edu.ufps.transaccion.models.responses;

import lombok.Data;

@Data
public class UserResponse {

    private boolean loginValido;

    private String username;
    
    private String email;
    
    private String mensaje;
    
    private String token;
}

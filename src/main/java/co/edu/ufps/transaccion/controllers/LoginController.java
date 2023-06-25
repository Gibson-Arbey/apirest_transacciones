package co.edu.ufps.transaccion.controllers;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.transaccion.entities.UserEntity;
import co.edu.ufps.transaccion.models.requests.UserRequest;
import co.edu.ufps.transaccion.models.responses.UserResponse;
import co.edu.ufps.transaccion.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final String secretKey = "yo_me_compre_un_47";

    @Autowired
    UserRepository userRepository;

    public UserResponse login(@RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        Optional<UserEntity> user = userRepository.findByUsername(userRequest.getUsername());
        if(user.isPresent()){
            if (user.get().getPass().equals(userRequest.getPass())) {
                BeanUtils.copyProperties(userResponse, user);
                userResponse.setMensaje("Bienvenido");
                userResponse.setLoginValido(true);
                userResponse.setEmail(user.get().getEmail());
                userResponse.setUsername(user.get().getUsername());
                String token = generarToken(userResponse.getUsername());
                userResponse.setToken(token);
                
            }
            else{
                userResponse.setLoginValido(false);
                userResponse.setMensaje("Usuario o contraseña invalidos");
            }
        }else{
            userResponse.setLoginValido(false);
            userResponse.setMensaje("El usuario no se encuentra registrado");
        }
        return userResponse;
    }


    private String generarToken(String username) {

        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hora

        String token =  Jwts.builder()
                            .setSubject(username)
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(expirationDate)
                            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                            .compact();

        return token;
    }

    
}


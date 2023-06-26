package co.edu.ufps.transaccion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.transaccion.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    public Optional<UserEntity> findByUsername(String username);
    
}

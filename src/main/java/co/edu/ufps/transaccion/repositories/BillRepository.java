package co.edu.ufps.transaccion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.ufps.transaccion.entities.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity,Integer> {
    
}

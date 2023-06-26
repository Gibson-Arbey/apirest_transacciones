package co.edu.ufps.transaccion.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.transaccion.entities.BillEntity;
import co.edu.ufps.transaccion.entities.UserEntity;
import co.edu.ufps.transaccion.models.requests.BillRequest;
import co.edu.ufps.transaccion.repositories.BillRepository;
import co.edu.ufps.transaccion.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BillRepository billRepository;

    @GetMapping("/{user}/bills")
    public List<BillEntity> getBillsbyUser(@PathVariable("user") String user) {
        List<BillEntity> bills = new ArrayList<>();
        Optional<UserEntity> usuarioOptional = userRepository.findByUsername(user);
        System.out.println("*************" +usuarioOptional.get().getUsername());
        if (usuarioOptional.isPresent()) {
            UserEntity usuario = usuarioOptional.get(); 
            bills = usuario.getBills();
        }
        return bills;
    }

    @PostMapping("/{user}/bills")
	public BillEntity guardarFactura(@PathVariable("user") String user, @RequestBody BillRequest billRequest) throws Exception {
        Optional<UserEntity> usuario = userRepository.findByUsername(user);
		if(usuario.isPresent()) {
            BillEntity bill = new BillEntity();
            LocalDate date = LocalDate.now();
            BeanUtils.copyProperties(billRequest, bill);
            bill.setUser(usuario.get());
            bill.setDateBill(date);
            
			return billRepository.save(bill);
		}else{
            throw new Exception("El usuario no existe");            
        }	
	}

    @DeleteMapping("/{user}/bills/{bill_id}")
    public BillEntity deleteBillbyUser(@PathVariable("user") String user, @PathVariable("bill_id") Integer bill_id) throws Exception {
        Optional<UserEntity> usuario = userRepository.findByUsername(user);
         if (usuario.isPresent()) {
        Optional<BillEntity> bill = billRepository.findById(bill_id);
        
        if (bill.isPresent()) {
            BillEntity billToDelete = bill.get();
            
            if (billToDelete.getUser().getUsername().equals(user)) {
                billRepository.deleteById(bill_id);
                return billToDelete;
            } else {
                throw new Exception("La factura no pertenece al usuario especificado");
            }
        } else {
            throw new Exception("La factura no existe");
        }
    } else {
        throw new Exception("El usuario no existe");
    }

    }


}

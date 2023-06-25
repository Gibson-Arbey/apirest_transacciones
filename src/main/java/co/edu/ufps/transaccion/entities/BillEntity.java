package co.edu.ufps.transaccion.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bill")
public class BillEntity {
    
    @Id
    @SequenceGenerator(name="bill_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bill_id_seq")
    private Integer id;

    @Column(name = "date_bill", nullable = false)
    private LocalDate dateBill;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity user;

    @NotNull
    private Float value;

    @NotNull
    private Integer type;

    @Column(length = 120, nullable = false)
    private String observation;
}

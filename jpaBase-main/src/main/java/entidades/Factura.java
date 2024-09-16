package entidades;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Table(name="Factura")
@Audited
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha")
    private String fecha;

    @Column(name = "numero")
    private int numero;
    @Column(name = "total")
    private int total;
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "fk_cliente")
    private Cliente cliente;
//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

    @OneToMany(mappedBy = "factura",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
    public Factura(){

    }

    public Factura(String fecha, int numero) {
        this.fecha = fecha;
        this.numero = numero;
    }

    public Factura(String fecha, int numero, Cliente cliente) {
        this.fecha = fecha;
        this.numero = numero;
        this.cliente = cliente;
    }

    public Factura(String fecha, int numero, Cliente cliente, List<DetalleFactura> detalles) {
        this.fecha = fecha;
        this.numero = numero;
        this.cliente = cliente;
        this.detalles = detalles;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Factura(String fecha, int numero, int total) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
    }

    public Factura(String fecha, int numero, int total, Cliente cliente, List<DetalleFactura> detalles) {
        this.fecha = fecha;
        this.numero = numero;
        this.total = total;
        this.cliente = cliente;
        this.detalles = detalles;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}

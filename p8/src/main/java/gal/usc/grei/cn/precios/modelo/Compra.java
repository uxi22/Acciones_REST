package gal.usc.grei.cn.precios.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "compras")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Compra {
    @Id
    private String id;
    private String fecha;
    @NotEmpty(message = "EL símbolo no puede ser vacío")
    private String simbolo;
    @NotNull(message = "El volumen no puede ser vacío")
    private Long volumen;
    @NotNull(message = "El precio por unidad no puede ser vacío")
    private Float unidad;
    @NotNull(message = "El precio total no puede ser vacío")
    private Float total;
    @NotNull(message = "El método de pago no puede ser vacío")
    private Pago metodo_pago;

    private Estado estado_compra;

    private Compra(String id, String fecha, String simbolo, Long volumen, Float unidad, Float total,Pago metodo_pago ,Estado estado_compra) {
        this.id = id;
        this.fecha = fecha;
        this.simbolo = simbolo;
        this.volumen = volumen;
        this.unidad = unidad;
        this.total = total;
        this.metodo_pago = metodo_pago;
        this.estado_compra = estado_compra;
    }

    //getters
    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public Long getVolumen() {
        return volumen;
    }

    public Float getUnidad() {
        return unidad;
    }

    public Float getTotal() {
        return total;
    }
    public Pago getMetodo_pago() {
        return metodo_pago;
    }

    public Estado getEstado_compra() {
        return estado_compra;
    }

    //setters con el patron builder
    public Compra setId(String id) {
        this.id = id;
        return this;
    }
    public Compra setFecha(String fecha) {
        this.fecha = fecha;
        return this;
    }
    public Compra setSimbolo(String simbolo) {
        this.simbolo = simbolo;
        return this;
    }
    public Compra setVolumen(Long volumen) {
        this.volumen = volumen;
        return this;
    }
    public Compra setUnidad(Float unidad) {
        this.unidad = unidad;
        return this;
    }
    public Compra setTotal(Float total) {
        this.total = total;
        return this;
    }
    public Compra setMetodo_pago(Pago metodo_pago) {
        this.metodo_pago = metodo_pago;
        return this;
    }
    public Compra setEstado_compra(Estado estado_compra) {
        this.estado_compra = estado_compra;
        return this;
    }


    //equals
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Compra compra)) {
            return false;
        }
        return id.equals(compra.id) && fecha.equals(compra.fecha) && simbolo.equals(compra.simbolo) && volumen.equals(compra.volumen) && unidad.equals(compra.unidad) && total.equals(compra.total);
    }

    //hashCode
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, fecha, simbolo, volumen, unidad, total);
    }

    //toString
    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ",\n fecha='" + getFecha() + "'" +
                ",\n simbolo='" + getSimbolo() + "'" +
                ",\n volumen='" + getVolumen() + "'" +
                ",\n unidad='" + getUnidad() + "'" +
                ",\n total='" + getTotal() + "'" +
                "}";
    }
}

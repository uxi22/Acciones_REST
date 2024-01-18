package gal.usc.grei.cn.precios.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="pagos")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pago {
    @NotEmpty(message = "EL número de tarjeta no puede ser vacío")
    private String tarjeta;
    @NotEmpty(message = "La fecha de vencimiento no puede ser vacío")
    private String vencimiento;
    @NotNull(message = "El CVV no puede ser vacío")
    private String cvv;

    public Pago(String tarjeta, String vencimiento, String cvv) {
        this.tarjeta = tarjeta;
        this.vencimiento = vencimiento;
        this.cvv = cvv;
    }

    //getters
    public String getTarjeta() {
        return tarjeta;
    }
    public String getVencimiento() {
        return vencimiento;
    }
    public String getCvv() {
        return cvv;
    }


    //setters con el patron builder
    public Pago setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
        return this;
    }
    public Pago setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
        return this;
    }
    public Pago setCvv(String cvv) {
        this.cvv = cvv;
        return this;
    }


    //equals
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pago pago)) {
            return false;
        }
        return java.util.Objects.equals(tarjeta, pago.tarjeta) && java.util.Objects.equals(vencimiento, pago.vencimiento) && java.util.Objects.equals(cvv, pago.cvv);
    }

    //hashCode
    @Override
    public int hashCode() {
        return java.util.Objects.hash(tarjeta, vencimiento, cvv);
    }

    //toString
    @Override
    public String toString() {
        return "{" +
            ",\n tarjeta='" + getTarjeta() + "'" +
            ",\n vencimiento='" + getVencimiento() + "'" +
            ",\n cvv='" + getCvv() + "'" +
            "}";
    }
}

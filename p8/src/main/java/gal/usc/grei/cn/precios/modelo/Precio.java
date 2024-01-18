package gal.usc.grei.cn.precios.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="precios")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Precio {
    @Id
    private String id;
    private String date;
    private String symbol;
    private Float open;
    private Float close;
    private Float low;
    private Float high;
    private Long volume;

    private Precio(String id, String date, String symbol, Float open, Float close, Float low, Float high, Long volume) {
        this.id = id;
        this.date = date;
        this.symbol = symbol;
        this.open = open;
        this.close = close;
        this.low = low;
        this.high = high;
        this.volume = volume;
    }

    //getters
    public String getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getSymbol() {
        return symbol;
    }
    public Float getOpen() {
        return open;
    }
    public Float getClose() {
        return close;
    }
    public Float getLow() {
        return low;
    }
    public Float getHigh() {
        return high;
    }
    public Long getVolume() {
        return volume;
    }


    //setters con el patron builder
    public Precio setId(String id) {
        this.id = id;
        return this;
    }
    public Precio setDate(String date) {
        this.date = date;
        return this;
    }
    public Precio setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }
    public Precio setOpen(Float open) {
        this.open = open;
        return this;
    }
    public Precio setClose(Float close) {
        this.close = close;
        return this;
    }
    public Precio setLow(Float low) {
        this.low = low;
        return this;
    }
    public Precio setHigh(Float high) {
        this.high = high;
        return this;
    }
    public Precio setVolume(Long volume) {
        this.volume = volume;
        return this;
    }



    //equals
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Precio)) {
            return false;
        }
        Precio precio = (Precio) o;
        return id.equals(precio.id) && date.equals(precio.date) && symbol.equals(precio.symbol) && open.equals(precio.open) && close.equals(precio.close) && low.equals(precio.low) && high.equals(precio.high) && volume.equals(precio.volume);
    }

    //hashcode
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + symbol.hashCode();
        result = 31 * result + open.hashCode();
        result = 31 * result + close.hashCode();
        result = 31 * result + low.hashCode();
        result = 31 * result + high.hashCode();
        result = 31 * result + volume.hashCode();
        return result;
    }
    //toString
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ",\n date='" + getDate() + "'" +
            ",\n symbol='" + getSymbol() + "'" +
            ",\n open='" + getOpen() + "'" +
            ",\n close='" + getClose() + "'" +
            ",\n low='" + getLow() + "'" +
            ",\n high='" + getHigh() + "'" +
            ",\n volume='" + getVolume() + "'" +
            "}";
    }

}

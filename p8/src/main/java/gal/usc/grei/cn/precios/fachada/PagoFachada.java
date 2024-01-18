package gal.usc.grei.cn.precios.fachada;


import gal.usc.grei.cn.precios.modelo.Estado;
import gal.usc.grei.cn.precios.modelo.Pago;
import gal.usc.grei.cn.precios.repositorio.CompraRepositorio;
import org.springframework.stereotype.Service;

@Service
public class PagoFachada {

    private final CompraRepositorio compras;

    /**
     * Constructor de la clase
     */
    public PagoFachada(CompraRepositorio compras) {
        this.compras = compras;
    }


    /**
     * Método que permite realizar un pago.
     *
     * @param id     El id de la compra a pagar.
     * @param metodo El método de pago a utilizar.
     * @return El estado de la compra una vez pagada.
     */
    public Estado pagar(String id, Pago metodo) {
        //Se recupera la compra por el id
        if (compras.existsById(id)) {
            //Se comprueba que la compra no esté ya pagada
            if (compras.findById(id).get().getEstado_compra() != Estado.COMPLETADA) {
                //Se comprueba que el método de pago sea válido
                if (metodo != null) {
//                    si los datos de la tarjeta son correctos
                    int anho = Integer.parseInt(metodo.getVencimiento().split("/")[1]);
                    int mes = Integer.parseInt(metodo.getVencimiento().split("/")[0]);
                    if (metodo.getTarjeta().matches("^\\d{4}-?\\d{4}-?\\d{4}-?\\d{4}$") && anho >= 23 && anho <= 30 && mes >= 1 && mes <= 12 && metodo.getCvv().matches("^\\d{3}$"))
                        return Estado.COMPLETADA;
                    else return Estado.CANCELADA;
                } else return Estado.EN_PROCESO;
            } else return Estado.COMPLETADA;
        } else return Estado.CANCELADA;
    }
}

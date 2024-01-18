package gal.usc.grei.cn.precios.controlador;

import gal.usc.grei.cn.precios.fachada.CompraFachada;
import gal.usc.grei.cn.precios.fachada.PagoFachada;
import gal.usc.grei.cn.precios.modelo.Compra;
import gal.usc.grei.cn.precios.modelo.Estado;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraControlador {
    //Referencia a la clase CompraFachada
    private final CompraFachada compras;
    private final PagoFachada pagos;

    /**
     * Constructor de la clase
     *
     * @param compras Instancia de la clase CompraFachada
     */
    @Autowired
    public CompraControlador(CompraFachada compras, PagoFachada pagos) {
        this.compras = compras;
        this.pagos = pagos;
    }


    /**
     * Método: POST
     * Url para llegar: /compras
     * Objetivo: insertar la compra que se facilita como parámetro.
     *
     * @param compra Los datos de la compra a insertar.
     * @return Si la inserción se ha podido hacer, la nueva compra y la url para acceder a ella.
     */
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> create(@Valid @RequestBody Compra compra) {
        //Tratamos de crear la compra:
        Optional<Compra> inserted = compras.create(compra);
        //Si se crea correctamente, devolvemos la información de la compra creada.
        if (inserted.isPresent()) {
            Estado estado = pagos.pagar(inserted.get().getId(), compra.getMetodo_pago());
            if (estado == Estado.CANCELADA){
                //se elimina la compra de la base de datos
                compras.get(inserted.get().getId()).ifPresent(compras::delete);
                return ResponseEntity.badRequest().body("No ha sido posible realizar la compra"); //400
            } else if (estado == Estado.COMPLETADA) {
                //se actualiza el estado de la compra en la base de datos
                inserted.get().setEstado_compra(Estado.COMPLETADA);
                //actualizamos la base de datos con el inserted con los nuevos datos
                compras.update(inserted.get());
                //compras.get(inserted.get().getId()).ifPresent(compras::update);
                //Se devuelve la compra
                return ResponseEntity.ok().body(inserted.get()); //201
            }
        }

        return ResponseEntity.badRequest().body("Error al crear una nueva compra"); //400
    }

    /**
     * Método: GET
     * Url para llegar: /compras
     * Objetivo: obtener una compra concreta por su id.
     * 
     * @param id El id de la compra a obtener.
     * @return Si la compra existe, la información de la compra.
     */
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) 
    ResponseEntity<?> get(@PathVariable("id") String id) {
        //Tratamos de obtener la compra:
        Optional<Compra> compra = compras.get(id);
        //Si existe, devolvemos la información de la compra.
        if (compra.isPresent()) {
            return ResponseEntity.ok().body(compra.get());
        } else return ResponseEntity.notFound().build(); //404
    }

}

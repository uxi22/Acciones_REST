package gal.usc.grei.cn.precios.fachada;

import gal.usc.grei.cn.precios.modelo.Compra;
import gal.usc.grei.cn.precios.modelo.Estado;
import gal.usc.grei.cn.precios.repositorio.CompraRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CompraFachada {
    //Referencias a las interfaces repository que necesitamos en esta clase:
    private final CompraRepositorio compras;

    /**
     * Constructor de la clase
     * @param compras Referencia al CompraRepositorio
     */
    public CompraFachada(CompraRepositorio compras) {
        this.compras = compras;
    }

    public Optional<Compra> get(String id){
        //Se recupera la compra por el id
        return compras.findById(id);
    }

    /**
     * Método que permite insertar una nueva compra en la base de datos.
     *
     * @param compra Los datos de la compra a insertar.
     * @return Los datos de la compra una vez insertados, incluyendo el id.
     * @throws ResponseStatusException Excepción lanzada en caso de que se facilite alguna información incorrecta.
     */
    public Optional<Compra> create(Compra compra){
        //Comprobamos que la compra haya llegado sin un id:
        if(compra.getId() == null || compra.getId().isEmpty()){
            //Si es así, le añadimos un id generado aleatoriamente
            compra.setId(java.util.UUID.randomUUID().toString()).setEstado_compra(Estado.EN_PROCESO);
            while (compras.existsById(compra.getId())){
                compra.setId(java.util.UUID.randomUUID().toString()).setEstado_compra(Estado.EN_PROCESO);
            }
            //Se inserta la compra en la base de datos (en proceso)
            return Optional.of(compras.insert(compra));
        }
        return Optional.empty();
    }

    public void delete(Compra compra1) {
        compras.delete(compra1);
    }

    public void update(Compra compra){
        compras.save(compra);
    }
}

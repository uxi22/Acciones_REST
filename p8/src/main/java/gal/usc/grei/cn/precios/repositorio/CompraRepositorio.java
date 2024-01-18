package gal.usc.grei.cn.precios.repositorio;

import gal.usc.grei.cn.precios.modelo.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompraRepositorio extends MongoRepository<Compra, String> {

}

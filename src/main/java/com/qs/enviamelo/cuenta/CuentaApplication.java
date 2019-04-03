package com.qs.enviamelo.cuenta;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qs.enviamelo.cuenta.entity.interfaces.impl.Tienda;

@SpringBootApplication
public class CuentaApplication {

	public static void main(String[] args) {
	    final Logger logger = Logger.getLogger("bitacora.subnivel.Utilidades");

		
		
		Tienda tienda = new Tienda();
		tienda.setNombre("Tienda de prueba");
		
		logger.log(Level.INFO, tienda.toString());
		
		
		

		
		SpringApplication.run(CuentaApplication.class, args);
	}

}

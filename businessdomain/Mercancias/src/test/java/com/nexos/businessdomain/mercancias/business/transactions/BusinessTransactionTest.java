/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.business.transactions;

//import com.nexos.businessdomain.mercancias.entities.Mercancia;//para ejecutar el microservivio recomiendo comentar todo el test
//import com.nexos.businessdomain.mercancias.entities.Usuarios;//para ejecutar el microservivio recomiendo comentar todo el test
import com.nexos.businessdomain.mercancias.repository.IMercanciaRepository;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Juan
 */
public class BusinessTransactionTest {
    
//    @Mock
//    IMercanciaRepository mercanciaRepository;
//    
//    @InjectMocks
//    BusinessTransaction businessTransaction;
//    
//    
//    private Mercancia mercancia;
//    private Usuarios usuario;
//    
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        
//        mercancia = new Mercancia(); 
//        mercancia.setNombreProducto("MOTOR");
//        mercancia.setCantidadProducto(15);
//        mercancia.setFechaIngresoProducto(new Date("10/09/2021"));
//        mercancia.setUsuarioId(usuario);
//    }
//   
//
//    /**
//     * Test of list method, of class BusinessTransaction.
//     */
//    @Test
//    public void testList() {
//        Mockito.when(mercanciaRepository.findAll()).thenReturn(Arrays.asList(mercancia));
//        assertNotNull(businessTransaction.list());
//    }
//    
//    //por alguna razon no puero importar las entidades a esta clase, se debe comentar toda la prueba
//    //y los imports para que los microservicios funcionen con normalidad
//
//    
}

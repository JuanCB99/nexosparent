/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexos.businessdomain.mercancias.entities.Mercancia;
import com.nexos.businessdomain.mercancias.entities.Usuarios;
import com.nexos.businessdomain.mercancias.repository.IMercanciaRepository;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.Collections;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 *
 * @author Juan
 */
@RestController
@RequestMapping("/mercancia")
public class MercanciaRestController {

    @Autowired
    IMercanciaRepository mercanciaRepository;

    private final WebClient.Builder webClientBuilder;

    public MercanciaRestController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    //define timeout
    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    @GetMapping()
    public ResponseEntity<List<Mercancia>> list() {
        List<Mercancia> mercancias = mercanciaRepository.findAll();

        if (mercancias == null || mercancias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mercancias);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mercancia> get(@PathVariable Integer id) {

        return mercanciaRepository.findById(id).
                map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/byUsuario")
    public ResponseEntity<List<Mercancia>> findByUsuario(@RequestBody Usuarios usuarioId) {

        List<Mercancia> mercancias = mercanciaRepository.findByUsuarioId(usuarioId);

        return ResponseEntity.status(HttpStatus.FOUND).body(mercancias);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<?> put(@PathVariable Integer idUsuario, @RequestBody Mercancia input) {

        mercanciaRepository.save(input);
        
        System.out.println("USUARIO QUE MODIFICO LA TABLA = " + idUsuario);

        return ResponseEntity.ok(input);
        
    }

    @PostMapping
    public ResponseEntity<Mercancia> post(@RequestBody Mercancia input) {
        Mercancia mercanciaSave = mercanciaRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaSave);
    }

    @GetMapping("/prueba/{idUsuario}")
    private Integer getUserById(Integer idUsuario) {

        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .baseUrl("http://businessdomain-usuario/usuarios")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://businessdomain-usuario/usuarios"))
                .build();

        JsonNode block = client.method(HttpMethod.GET).uri("/" + idUsuario)
                .retrieve().bodyToMono(JsonNode.class).block();

        //Usuarios usuarioRecuperado = new Usuarios();
        //usuarioRecuperado.setNombreUsuario(block.get("nombreUsuario").asText()); 
        System.out.println("DATOS DE USUARIO REMOTO = "
                + block.get("idUsuario").asInt() + "Nombre: "
                + block.get("nombreUsuario").asText());
        return block.get("idUsuario").asInt();

    }

    @DeleteMapping("/{idMercancia}/{idUsuario}")
    public ResponseEntity<?> delete(@PathVariable Integer idMercancia, @PathVariable Integer idUsuario) {

        if (get(idMercancia).getBody() == null) {
            return ResponseEntity.notFound().build();
        }

        Mercancia mercanciaRecuperada = get(idMercancia).getBody();

        if (mercanciaRecuperada.getUsuarioId().getIdUsuario() == idUsuario) {

            mercanciaRepository.delete(mercanciaRecuperada);
            return ResponseEntity.ok(mercanciaRecuperada);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mercanciaRecuperada);
        }
    }

}

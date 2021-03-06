/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.business.transactions;

import com.fasterxml.jackson.databind.JsonNode;
import com.nexos.businessdomain.mercancias.entities.Mercancia;
import com.nexos.businessdomain.mercancias.exception.BusinessRuleException;
import com.nexos.businessdomain.mercancias.repository.IMercanciaRepository;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 *
 * @author Juan
 */
@Service
public class BusinessTransaction {

    @Autowired
    IMercanciaRepository mercanciaRepository;

    private final WebClient.Builder webClientBuilder;

    public BusinessTransaction(WebClient.Builder webClientBuilder) {
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

    public ResponseEntity<List<Mercancia>> list() {
        List<Mercancia> mercancias = mercanciaRepository.findAll();

        if (mercancias == null || mercancias.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(mercancias);
        }
    }

    public boolean existUser(Integer id) {

        return (getUserById(id) != null) ? true : false;

    }

    public ResponseEntity<?> delete(Integer idMercancia, Integer idUsuario) {

        if (mercanciaRepository.findById(idMercancia).get() == null) {
            return ResponseEntity.notFound().build();
        }

        Mercancia mercanciaRecuperada = mercanciaRepository.findById(idMercancia).get();

        if (mercanciaRecuperada.getUsuarioId().getIdUsuario() == idUsuario) {

            mercanciaRepository.delete(mercanciaRecuperada);
            return ResponseEntity.ok(mercanciaRecuperada);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mercanciaRecuperada);
        }
    }

    public ResponseEntity<?> put(Integer idUsuario, Mercancia input) {

        if (existUser(idUsuario)) {

            mercanciaRepository.save(input);

            System.out.println("USUARIO QUE MODIFICO LA TABLA = " + idUsuario);
            return ResponseEntity.ok(input);

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Mercancia> save(@RequestBody Mercancia input) throws BusinessRuleException {
        if (existUser(input.getUsuarioId().getIdUsuario())) {
            Mercancia mercanciaSave = mercanciaRepository.save(input);
            return ResponseEntity.status(HttpStatus.CREATED).body(mercanciaSave);
        } else {
            BusinessRuleException exception = new BusinessRuleException("1500", "Error de validacion, no se encontro el registro", HttpStatus.PRECONDITION_FAILED);
            throw exception;
        }

    }

    private Integer getUserById(Integer idUsuario) {

        WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .baseUrl("http://localhost:8080/usuarios")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080/usuarios"))
                .build();

        JsonNode block = client.method(HttpMethod.GET).uri("/" + idUsuario)
                .retrieve().bodyToMono(JsonNode.class).block();

        System.out.println("DATOS DE USUARIO REMOTO = "
                + block.get("idUsuario").asInt() + "Nombre: "
                + block.get("nombreUsuario").asText());
        return block.get("idUsuario").asInt();

    }

}

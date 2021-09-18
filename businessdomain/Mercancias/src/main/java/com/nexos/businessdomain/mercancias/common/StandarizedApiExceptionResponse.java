/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexos.businessdomain.mercancias.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Juan
 */
@Getter
@Setter
public class StandarizedApiExceptionResponse {

    @ApiModelProperty(notes = "El identificador de la URI categoriza el error", name = "type",
            required = true, example = "/errors/authentication/not-authorized")
    private String type = "/errors/uncategorized";
    @ApiModelProperty(notes = "Mensaje que describa brevemente el error", name = "title",
            required = true, example = "El usuario no  esta autorizado")
    private String title;
    @ApiModelProperty(notes = "Codigo unico de error", name = "code",
            required = false, example = "1900")
    private String code;
    @ApiModelProperty(notes = "Explicacion entendible del error", name = "detail",
            required = true, example = "El usuario no tiene los permisos necesarios para acceder al recurso "
            + ", contactese por el siguiente medio: https://paginaSoporte.com")
    private String detail;
    @ApiModelProperty(notes = "Un URI que identifica la ocurrencia específica del error.", name = "detail",
            required = true, example = "/errors/authentication/not-authorized/01")
    private String instance = "/errors/uncategorized/bank";

    public StandarizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }

}

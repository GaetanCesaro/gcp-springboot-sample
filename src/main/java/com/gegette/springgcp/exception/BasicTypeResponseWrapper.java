package com.gegette.springgcp.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class BasicTypeResponseWrapper<T extends Serializable> implements Serializable {

    private T resultat;

}

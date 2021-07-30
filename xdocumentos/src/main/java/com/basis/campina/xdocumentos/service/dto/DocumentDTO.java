package com.basis.campina.xdocumentos.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO implements Serializable {

    private String file;

    private String uuId;
}

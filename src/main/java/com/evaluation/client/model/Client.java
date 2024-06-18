package com.evaluation.client.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.evaluation.client.utils.Constants;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = Constants.FIELD_IS_REQUIRED)
    @NotBlank(message = Constants.BLANCK_FIELD_PROVIDED)
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = Constants.INVALID_CHARACTERS)
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = Constants.FIELD_IS_REQUIRED)
    @NotBlank(message = Constants.BLANCK_FIELD_PROVIDED)
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = Constants.INVALID_CHARACTERS)
    private String apellidoPaterno;

    @Column(nullable = false)
    @NotNull(message = Constants.FIELD_IS_REQUIRED)
    @NotBlank(message = Constants.BLANCK_FIELD_PROVIDED)
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = Constants.INVALID_CHARACTERS)
    private String apellidoMaterno;

    @Column(nullable = false)
    //Validar si es fecha anterior a la actual, validar el formato de fecha tal vez DD/MM/YYYY
    private Date fechaNacimiento;

    @Column(nullable = false, unique = true)
    private String rfc;

    //Validar Lada, digitos con N tamanio, unicamente digitos
    private String numeroCelular;
    
    @Email(message = "Email tiene un formato inválido")
    private String email;

}

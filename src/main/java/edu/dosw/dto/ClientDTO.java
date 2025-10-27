package edu.dosw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un cliente")
public class ClientDTO {

    @Schema(description = "ID del cliente", example = "1")
    private int id;

    @Schema(description = "Nombre del cliente", example = "Juan Pérez", required = true)
    private String name;

    @Schema(description = "Email del cliente", example = "juan@example.com")
    private String email;

    @Schema(description = "Teléfono del cliente", example = "+1234567890")
    private String phone;

    @Schema(description = "Dirección del cliente", example = "Calle Principal 123")
    private String address;


}
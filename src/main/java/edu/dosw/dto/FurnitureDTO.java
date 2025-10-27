package edu.dosw.dto;

import edu.dosw.model.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa un mueble")
public class FurnitureDTO {

    @Schema(description = "ID del mueble", example = "1")
    private int id;

    @Schema(description = "Nombre del mueble", example = "Silla clásica", required = true)
    private String name;

    @Schema(description = "Precio unitario del mueble", example = "150.50")
    private double price;

    @Schema(description = "Estilo del mueble", example = "CLASSIC")
    private Style style;

    @Schema(description = "Descripción del mueble", example = "Silla de madera maciza")
    private String description;


    public FurnitureDTO(int id, String name, double price, Style style) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.style = style;
        this.description = "";
    }
}
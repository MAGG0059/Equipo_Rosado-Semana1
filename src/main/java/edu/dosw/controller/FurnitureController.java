package edu.dosw.controller;

import edu.dosw.dto.FurnitureDTO;
import edu.dosw.model.Style;
import edu.dosw.service.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/muebles")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @GetMapping
    public ResponseEntity<List<FurnitureDTO>> getFurnitureCatalog(
            @RequestParam(required = false) Style style,
            @RequestParam(required = false) Double maxPrice) {

        try {
            List<FurnitureDTO> furniture = furnitureService.getFurnitureCatalog(style, maxPrice);
            return ResponseEntity.ok(furniture);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<FurnitureDTO> addFurniture(@RequestBody FurnitureDTO furnitureDTO) {
        try {
            FurnitureDTO newFurniture = furnitureService.addFurniture(furnitureDTO);
            return ResponseEntity.ok(newFurniture);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
package edu.dosw.service;

import edu.dosw.dto.FurnitureDTO;
import edu.dosw.model.Style;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FurnitureService {

    public List<FurnitureDTO> getFurnitureCatalog(Style style, Double maxPrice) {
        List<FurnitureDTO> allFurniture = createSampleFurniture();

        if (style != null) {
            allFurniture = allFurniture.stream()
                    .filter(f -> f.getStyle() == style)
                    .collect(Collectors.toList());
        }

        if (maxPrice != null) {
            allFurniture = allFurniture.stream()
                    .filter(f -> f.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }

        return allFurniture;
    }

    public FurnitureDTO addFurniture(FurnitureDTO furnitureDTO) {

        furnitureDTO.setId(generateNewId());
        return furnitureDTO;
    }

    private List<FurnitureDTO> createSampleFurniture() {
        List<FurnitureDTO> furniture = new ArrayList<>();


        FurnitureDTO f1 = new FurnitureDTO();
        f1.setId(1);
        f1.setName("Silla clásica");
        f1.setPrice(150.0);
        f1.setStyle(Style.CLASSIC);
        f1.setDescription("Silla de madera maciza estilo clásico");
        furniture.add(f1);

        FurnitureDTO f2 = new FurnitureDTO();
        f2.setId(2);
        f2.setName("Mesa moderna");
        f2.setPrice(320.0);
        f2.setStyle(Style.MODERN);
        f2.setDescription("Mesa de centro con diseño moderno");
        furniture.add(f2);

        FurnitureDTO f3 = new FurnitureDTO();
        f3.setId(3);
        f3.setName("Sofá contemporáneo");
        f3.setPrice(850.0);
        f3.setStyle(Style.CONTEMPORARY);
        f3.setDescription("Sofá de tres plazas estilo contemporáneo");
        furniture.add(f3);

        return furniture;
    }

    private int generateNewId() {
        return (int) (Math.random() * 1000) + 100;
    }
}
package edu.dosw.service;

import edu.dosw.model.Furniture;
import edu.dosw.model.Style;
import edu.dosw.dto.FurnitureDTO;
import edu.dosw.repository.FurnitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FurnitureService {

    @Autowired
    private FurnitureRepository furnitureRepository;

    public List<FurnitureDTO> getFurnitureCatalog(Style style, Double maxPrice) {
        List<Furniture> furniture;

        if (style != null && maxPrice != null) {
            furniture = furnitureRepository.findAll().stream().filter(f -> f.getStyle() == style && f.getPrice() <= maxPrice).collect(Collectors.toList());
        } else if (style != null) {
            furniture = furnitureRepository.findByStyle(style);
        } else if (maxPrice != null) {
            furniture = furnitureRepository.findByPriceLessThanEqual(maxPrice);
        } else {
            furniture = furnitureRepository.findAll();
        }

        return furniture.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Furniture getFurnitureEntityById(int id) {
        return furnitureRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el mueble"));
    }

    public List<Furniture> getFurnitureEntitiesByIds(List<Integer> ids) {
        return furnitureRepository.findByIds(ids);
    }

    public FurnitureDTO addFurniture(FurnitureDTO furnitureDTO) {
        Furniture furniture = new Furniture(0, furnitureDTO.getName(), furnitureDTO.getPriceUnit(), furnitureDTO.getStyle()) {};
        Furniture savedFurniture = furnitureRepository.save(furniture);
        return convertToDTO(savedFurniture);
    }

    private FurnitureDTO convertToDTO(Furniture furniture) {
        return new FurnitureDTO(
                furniture.getId(),
                furniture.getName(),
                furniture.getPriceUnit(),
                furniture.getStyle()
        );
    }
}
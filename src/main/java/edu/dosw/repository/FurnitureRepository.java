package edu.dosw.repository;

import edu.dosw.model.Furniture;
import edu.dosw.model.Style;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FurnitureRepository {

    private List<Furniture> furnitureCatalog = new ArrayList<>();
    private int nextFurnitureId = 1;

    public FurnitureRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        furnitureCatalog.add(new Furniture(nextFurnitureId++, "Silla Clásica", 150000, Style.CLASSIC) {});
        furnitureCatalog.add(new Furniture(nextFurnitureId++, "Mesa Moderna", 450000, Style.MODERN) {});
        furnitureCatalog.add(new Furniture(nextFurnitureId++, "Sofá Rústico", 780000, Style.RUSTIC) {});
        furnitureCatalog.add(new Furniture(nextFurnitureId++, "Silla Moderna", 180000, Style.MODERN) {});
        furnitureCatalog.add(new Furniture(nextFurnitureId++, "Mesa Clásica", 320000, Style.CLASSIC) {});
    }

    public List<Furniture> findAll() {
        return new ArrayList<>(furnitureCatalog);
    }

    public List<Furniture> findByStyle(Style style) {
        return furnitureCatalog.stream()
                .filter(f -> f.getStyle() == style)
                .collect(Collectors.toList());
    }

    public List<Furniture> findByPriceLessThanEqual(double maxPrice) {
        return furnitureCatalog.stream()
                .filter(f -> f.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Optional<Furniture> findById(int id) {
        return furnitureCatalog.stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    public List<Furniture> findByIds(List<Integer> ids) {
        return furnitureCatalog.stream()
                .filter(f -> ids.contains(f.getId()))
                .collect(Collectors.toList());
    }

    public Furniture save(Furniture furniture) {
        if (furniture.getId() == 0) {
            furniture = new Furniture(nextFurnitureId++, furniture.getName(),
                    furniture.getPriceUnit(), furniture.getStyle()) {};
        }
        furnitureCatalog.add(furniture);
        return furniture;
    }
}
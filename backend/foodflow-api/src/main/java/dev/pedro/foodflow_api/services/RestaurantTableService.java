package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableRequestDTO;
import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableResponseDTO;
import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableUpdateDTO;
import dev.pedro.foodflow_api.entities.RestaurantTable;
import dev.pedro.foodflow_api.exceptions.RestaurantTableNotFoundException;
import dev.pedro.foodflow_api.mappers.RestaurantTableMapper;
import dev.pedro.foodflow_api.repositories.RestaurantTableRepository;
import dev.pedro.foodflow_api.services.qrcode.QRCodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableMapper restaurantTableMapper;
    private final QRCodeService qrCodeService;

    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository, RestaurantTableMapper restaurantTableMapper, QRCodeService qrCodeService) {
        this.restaurantTableRepository = restaurantTableRepository;
        this.restaurantTableMapper = restaurantTableMapper;
        this.qrCodeService = qrCodeService;
    }

    public RestaurantTableResponseDTO createTable(RestaurantTableRequestDTO request) {
        var restaurantTable = RestaurantTable.builder().number(request.number()).build();
        var savedTable = restaurantTableRepository.save(restaurantTable);
        qrCodeService.generateForTable(savedTable);
        return restaurantTableMapper.toDTO(savedTable);
    }

    public List<RestaurantTableResponseDTO> listTables() {
        return restaurantTableRepository.findAll().stream().map(restaurantTableMapper::toDTO).toList();
    }

    public RestaurantTableResponseDTO getTableById(Long id) {
        return restaurantTableMapper.toDTO(restaurantTableRepository.findById(id)
                .orElseThrow(RestaurantTableNotFoundException::new));
    }

    public RestaurantTableResponseDTO getTableByNumber(Integer number) {
        return restaurantTableMapper.toDTO(restaurantTableRepository.findByNumber(number)
                .orElseThrow(RestaurantTableNotFoundException::new));
    }

    public RestaurantTableResponseDTO getTableByPublicId(UUID publicId) {
        return restaurantTableMapper.toDTO(restaurantTableRepository.findByPublicId(publicId)
                .orElseThrow(RestaurantTableNotFoundException::new));
    }

    public RestaurantTableResponseDTO updateTableFree(Long id, RestaurantTableUpdateDTO newStatus) {
        var restaurantTable = restaurantTableRepository.findById(id)
                .orElseThrow(RestaurantTableNotFoundException::new);
        restaurantTable.setFree(newStatus.free());
        restaurantTableRepository.save(restaurantTable);
        return restaurantTableMapper.toDTO(restaurantTable);
    }

    public RestaurantTable getEntityTableById(Long id) {
        return restaurantTableRepository.findById(id)
                .orElseThrow(RestaurantTableNotFoundException::new);
    }

    public void deleteTable(Long id) {
        restaurantTableRepository.delete(restaurantTableRepository.findById(id)
                .orElseThrow(RestaurantTableNotFoundException::new));

    }

}

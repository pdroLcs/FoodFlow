package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableRequestDTO;
import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableResponseDTO;
import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableUpdateDTO;
import dev.pedro.foodflow_api.mappers.RestaurantTableMapper;
import dev.pedro.foodflow_api.services.RestaurantTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Tag(name = "mesas", description = "Endpoints responsáveis pelo gerenciamento das mesas do restaurante")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/mesas")
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    public RestaurantTableController(RestaurantTableService restaurantTableService, RestaurantTableMapper restaurantTableMapper) {
        this.restaurantTableService = restaurantTableService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as mesas cadastradas no banco")
    public ResponseEntity<List<RestaurantTableResponseDTO>> listRestaurantTables() {
        return ResponseEntity.ok(restaurantTableService.listTables());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a mesa com base no Id")
    public ResponseEntity<RestaurantTableResponseDTO> getRestaurantTableById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantTableService.getTableById(id));
    }

    @GetMapping("/numero/")
    @Operation(summary = "Retorna a mesa com base no número")
    public ResponseEntity<RestaurantTableResponseDTO> getRestaurantTableByNumber(@RequestParam(name = "n") Integer number) {
        return ResponseEntity.ok(restaurantTableService.getTableByNumber(number));
    }

    @GetMapping("/public/{publicId}")
    @Operation(summary = "Retorna a mesa com base no Id público")
    public ResponseEntity<RestaurantTableResponseDTO> getRestaurantByPublicId(@PathVariable UUID publicId) {
        return ResponseEntity.ok(restaurantTableService.getTableByPublicId(publicId));
    }

    @GetMapping("public/{publicId}/qrcode")
    @Operation(summary = "Retorna o QR-Code da mesa com base no Id público")
    public ResponseEntity<Resource> getQRCode(@PathVariable UUID publicId) throws IOException {
        var path = Paths.get("qrcodes/" + publicId + ".png");
        var resource = new UrlResource(path.toUri());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @PostMapping
    @Operation(summary = "Cria uma nova mesa e salva no banco")
    public ResponseEntity<RestaurantTableResponseDTO> createRestaurantTable(@RequestBody @Valid RestaurantTableRequestDTO request) {
        var restaurantTable = restaurantTableService.createTable(request);
        var uri = URI.create("/mesas/" + restaurantTable.id());
        return ResponseEntity.created(uri).body(restaurantTable);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza o atributo free da mesa")
    public ResponseEntity<RestaurantTableResponseDTO> updateRestaurantTableStatus(@PathVariable Long id, @RequestBody RestaurantTableUpdateDTO request) {
        return ResponseEntity.ok(restaurantTableService.updateTableFree(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma mesa do banco com base no Id")
    public ResponseEntity<Void> deleteRestaurantTable(@PathVariable Long id) {
        restaurantTableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

}

package dev.pedro.foodflow_api.dto.restauranttable;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantTableResponseDTO {

        private Long id;
        private Integer number;
        private boolean occupied;
        private UUID publicId;

}

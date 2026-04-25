package dev.pedro.foodflow_api.services.qrcode;

import dev.pedro.foodflow_api.entities.RestaurantTable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class QRCodeService {

    public void generateForTable(RestaurantTable table) {
        try {
            String url = "http://localhost:5172/mesas/" + table.getPublicId();
            String apiUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + url;

            InputStream input = new URL(apiUrl).openStream();
            var path = Paths.get("qrcodes/" + table.getPublicId() + ".png");
            Files.createDirectories(path.getParent());
            Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

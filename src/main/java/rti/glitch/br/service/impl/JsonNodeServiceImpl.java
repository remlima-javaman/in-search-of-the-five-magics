package rti.glitch.br.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import rti.glitch.br.service.JsonNodeService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@AllArgsConstructor
public class JsonNodeServiceImpl implements JsonNodeService {

    private final ResourceLoader resourceLoader;

    @Override
    public String getJsonNode(String key) {

        Resource resource = this.resourceLoader.getResource("classpath:"+key+".json");

        if (!resource.exists())
            throw new RuntimeException("Resource not found: " + key);

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

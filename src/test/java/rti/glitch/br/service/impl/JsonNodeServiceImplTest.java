package rti.glitch.br.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class JsonNodeServiceImplTest {

    @Test
    void getJsonNode() throws IOException {

      Resource resource = new ClassPathResource("campo-espiritual.json");

      if (!resource.exists())
          throw new FileNotFoundException();

      StringBuilder stringBuilder = new StringBuilder();
      try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()))) {
          String line;
          while ((line = bufferedReader.readLine()) != null) {
              stringBuilder.append(line).append("\n");
          }
      }

        System.out.println(stringBuilder);
    }
}
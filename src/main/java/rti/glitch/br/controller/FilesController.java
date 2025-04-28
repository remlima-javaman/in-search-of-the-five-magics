package rti.glitch.br.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FilesController {

    @GetMapping("/files/{nameImage}")
    public ResponseEntity<InputStreamResource> getImagem(@PathVariable String nameImage) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("files/" + nameImage);
            assert inputStream != null;
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(inputStream.readAllBytes()));
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.academiadodesenvolvedor.market.service;

import com.academiadodesenvolvedor.market.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.market.models.Media;
import com.academiadodesenvolvedor.market.services.contracts.MediaServiceContract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Teste da MediaService")
public class MediaServiceTest {

    @Autowired
    private MediaServiceContract service;

    @Test
    @DisplayName("Precisa criar uma Mída")
    public void shouldCreateAMedia(){
        Media media = new Media();
        media.setFilename("teste.png");
        media.setFilePath("storage/media/test");

        Media mediaStoraged = this.service.createMedia(media);

        Assertions.assertEquals("teste.png", mediaStoraged.getFilename());
        Assertions.assertEquals("storage/media/test", mediaStoraged.getFilePath());
        Assertions.assertNotNull(mediaStoraged.getId());
        Assertions.assertTrue(mediaStoraged.getId() > 0l);
    }

    @Test
    @DisplayName("Precisa apagar uma Mída")
    public void shouldDeleteAMedia(){
        Media media = new Media();
        media.setFilename("teste.png");
        media.setFilePath("/storage/test");
        this.service.createMedia(media);

        Long id = media.getId();
        this.service.deleteMedia(media);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.service.getMediaById(id);
        });
    }

    @Test
    @DisplayName("Precisa obter uma Mídia por Id")
    public void shouldGetAMediaById(){
        Media media = new Media();
        media.setFilename("teste.png");
        media.setFilePath("/storage/test");
        this.service.createMedia(media);

        Assertions.assertDoesNotThrow(() -> {
            Media mediaStoraged = this.service.getMediaById(media.getId());
            Assertions.assertNotNull(mediaStoraged.getId());
        });

    }

    @Test
    @DisplayName("Precisa lançar exceção quando Id não exstir")
    public void shouldThrowsExceptionWhenIdNotFound(){
        Assertions.assertThrows(ResourceNotFoundException.class, ()->{
            this.service.getMediaById(Long.MAX_VALUE);
        });
    }
}

package org.evitorsilva.controllers;

import org.evitorsilva.entities.GenresEntity;
import org.evitorsilva.services.MediaService;
import org.evitorsilva.util.DTO.response.MediaResponse;
import org.evitorsilva.util.Enums.EMedia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class MediaControllerTest {

    @Mock
    private MediaService mediaService;

    @InjectMocks
    private MediaController mediaController;

    @Test
    void getTitle() throws Exception {
        String tituloBusca = "Interstellar";

        Set<GenresEntity> generos = new HashSet<>();
        MediaResponse mockResponse = new MediaResponse(
                tituloBusca,
                169.0,
                EMedia.MOVIE,
                1,
                1,
                generos
        );

        Mockito.when(mediaService.get(tituloBusca)).thenReturn(Optional.of(mockResponse));

        ResponseEntity<MediaResponse> response = mediaController.getTitle(tituloBusca);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(tituloBusca, response.getBody().title());
    }
}
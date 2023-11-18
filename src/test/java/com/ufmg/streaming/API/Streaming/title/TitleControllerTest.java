package com.ufmg.streaming.API.Streaming.title;

import com.ufmg.streaming.API.Streaming.titles.Title;
import com.ufmg.streaming.API.Streaming.titles.TitleController;
import com.ufmg.streaming.API.Streaming.titles.TitleRequest;
import com.ufmg.streaming.API.Streaming.titles.TitleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TitleControllerTest {

    @Mock
    private TitleService titleService;

    @InjectMocks
    private TitleController titleController;

    public TitleControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTitle() {
        TitleRequest request = new TitleRequest(/* your request initialization */);
        ResponseEntity<String> response = titleController.createTitle(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Título criado com sucesso!", response.getBody());

        // Verifica se o método createTitle foi chamado no serviço
        verify(titleService, times(1)).createTitle(request);
    }

    @Test
    public void testFindTitleById() {
        Integer idTitle = 1;
        Title title = new Title(/* your title initialization */);
        when(titleService.findTitleById(idTitle)).thenReturn(title);

        ResponseEntity<Title> response = titleController.findTitleById(idTitle);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(title, response.getBody());

        // Verifica se o método findTitleById foi chamado no serviço
        verify(titleService, times(1)).findTitleById(idTitle);
    }

    @Test
    public void testFindTitleByIdNotFound() {
        Integer idTitle = 1;
        when(titleService.findTitleById(idTitle)).thenReturn(null);

        ResponseEntity<Title> response = titleController.findTitleById(idTitle);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());

        // Verifica se o método findTitleById foi chamado no serviço
        verify(titleService, times(1)).findTitleById(idTitle);
    }

    @Test
    public void testFindAllTitles() {
        List<Title> titles = Arrays.asList(new Title(/* your title initialization */));
        when(titleService.findAllTitles()).thenReturn(titles);

        ResponseEntity<List<Title>> response = titleController.findAllTitles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(titles, response.getBody());

        // Verifica se o método findAllTitles foi chamado no serviço
        verify(titleService, times(1)).findAllTitles();
    }

    @Test
    public void testFindAllTitlesNotFound() {
        when(titleService.findAllTitles()).thenReturn(null);

        ResponseEntity<List<Title>> response = titleController.findAllTitles();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());

        // Verifica se o método findAllTitles foi chamado no serviço
        verify(titleService, times(1)).findAllTitles();
    }
}

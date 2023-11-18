package com.ufmg.streaming.API.Streaming.title;

import com.ufmg.streaming.API.Streaming.titles.Title;
import com.ufmg.streaming.API.Streaming.titles.TitleRepository;
import com.ufmg.streaming.API.Streaming.titles.TitleRequest;
import com.ufmg.streaming.API.Streaming.titles.TitleService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class TitleServiceTest {

    @Mock
    private TitleRepository titleRepository;

    @InjectMocks
    private TitleService titleService;

    public TitleServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllTitles() {
        List<Title> titles = Arrays.asList(new Title(/* your title initialization */));
        when(titleRepository.findAll()).thenReturn(titles);

        List<Title> result = titleService.findAllTitles();

        assertNotNull(result);
        assertEquals(titles.size(), result.size());

        // Verifica se o método findAll foi chamado no repositório
        verify(titleRepository, times(1)).findAll();
    }

    @Test
    public void testCreateTitle() {
        TitleRequest request = new TitleRequest(/* your request initialization */);
        titleService.createTitle(request);

        // Verifica se o método save foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).save(any(Title.class));
    }

    @Test
    public void testFindTitleById() {
        Integer idTitle = 1;
        Title title = new Title(/* your title initialization */);
        when(titleRepository.findById(idTitle)).thenReturn(Optional.of(title));

        Title result = titleService.findTitleById(idTitle);

        assertNotNull(result);
        assertEquals(title, result);

        // Verifica se o método findById foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).findById(idTitle);
    }

    @Test
    public void testFindTitleByIdNotFound() {
        Integer idTitle = 1;
        when(titleRepository.findById(idTitle)).thenReturn(Optional.empty());

        Title result = titleService.findTitleById(idTitle);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).findById(idTitle);
    }

    @Test
    public void testEditTitle() {
        Integer idTitle = 1;
        TitleRequest request = new TitleRequest(/* your request initialization */);
        Title existingTitle = new Title(/* your existing title initialization */);
        when(titleRepository.findById(idTitle)).thenReturn(Optional.of(existingTitle));
        when(titleRepository.save(existingTitle)).thenReturn(existingTitle);

        Title result = titleService.editTitle(request, idTitle);

        assertNotNull(result);
        assertEquals(request.getTitle_name(), result.getTitle_name());

        // Verifica se o método findById foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).findById(idTitle);

        // Verifica se o método save foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).save(existingTitle);
    }

    @Test
    public void testEditTitleNotFound() {
        Integer idTitle = 1;
        TitleRequest request = new TitleRequest(/* your request initialization */);
        when(titleRepository.findById(idTitle)).thenReturn(Optional.empty());

        Title result = titleService.editTitle(request, idTitle);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).findById(idTitle);

        // Verifica se o método save não foi chamado (já que o título não foi encontrado)
        verify(titleRepository, never()).save(any(Title.class));
    }

    @Test
    public void testDeleteTitle() {
        Title title = new Title(/* your title initialization */);
        titleService.deleteTitle(title);

        // Verifica se o método delete foi chamado no repositório com o argumento correto
        verify(titleRepository, times(1)).delete(title);
    }
}
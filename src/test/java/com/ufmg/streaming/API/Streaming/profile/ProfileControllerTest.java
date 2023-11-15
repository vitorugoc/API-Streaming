package com.ufmg.streaming.API.Streaming.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProfileControllerTest {

    @InjectMocks
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProfile() {
        ProfileRequest profileRequest = new ProfileRequest();
        ResponseEntity<String> responseEntity = profileController.createProfile(profileRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Perfil criado com sucesso!", responseEntity.getBody());

        // Verifica se o método createProfile foi chamado no serviço
        verify(profileService, times(1)).createProfile(eq(profileRequest));
    }

    @Test
    public void testFindProfileById() {
        Integer idProfile = 1;
        Profile profile = new Profile();
        when(profileService.findProfileById(idProfile)).thenReturn(profile);

        ResponseEntity<Profile> responseEntity = profileController.findProfileById(idProfile);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(profile, responseEntity.getBody());

        // Verifica se o método findProfileById foi chamado no serviço
        verify(profileService, times(1)).findProfileById(eq(idProfile));
    }

    @Test
    public void testEditProfile() {
        Integer idProfile = 1;
        ProfileRequest profileRequest = new ProfileRequest();
        Profile editedProfile = new Profile();

        when(profileService.editProfile(eq(profileRequest), eq(idProfile))).thenReturn(editedProfile);

        ResponseEntity<Profile> responseEntity = profileController.editProfile(idProfile, profileRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(editedProfile, responseEntity.getBody());

        // Verifica se o método editProfile foi chamado no serviço
        verify(profileService, times(1)).editProfile(eq(profileRequest), eq(idProfile));
    }

    @Test
    public void testDeleteProfile() {
        Integer idProfile = 1;
        Profile toDeleteProfile = new Profile();

        when(profileService.findProfileById(idProfile)).thenReturn(toDeleteProfile);

        ResponseEntity<String> responseEntity = profileController.deleteProfile(idProfile);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Perfil deletado com sucesso!", responseEntity.getBody());

        // Verifica se o método findProfileById e deleteProfile foram chamados no serviço
        verify(profileService, times(1)).findProfileById(eq(idProfile));
        verify(profileService, times(1)).deleteProfile(eq(toDeleteProfile));
    }
}

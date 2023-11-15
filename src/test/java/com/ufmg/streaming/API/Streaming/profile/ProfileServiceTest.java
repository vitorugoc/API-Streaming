package com.ufmg.streaming.API.Streaming.profile;

import com.ufmg.streaming.API.Streaming.user.User;
import com.ufmg.streaming.API.Streaming.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    @InjectMocks
    private ProfileService profileService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProfileById() {
        Integer idProfile = 1;
        Profile profile = Profile.builder().build();

        when(profileRepository.findById(idProfile)).thenReturn(Optional.of(profile));

        Profile result = profileService.getProfileById(idProfile);

        assertNotNull(result);
        assertEquals(profile, result);

        // Verifica se o método findById foi chamado no repositório
        verify(profileRepository, times(1)).findById(eq(idProfile));
    }

    @Test
    public void testGetProfileByIdNotFound() {
        Integer idProfile = 1;

        when(profileRepository.findById(idProfile)).thenReturn(Optional.empty());

        Profile result = profileService.getProfileById(idProfile);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório
        verify(profileRepository, times(1)).findById(eq(idProfile));
    }

    @Test
    public void testCreateProfile() {
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setUser(new User());
        Profile profile = Profile.builder().build();

        profileService.createProfile(profileRequest);

        // Verifica se o método save foi chamado no repositório
        verify(profileRepository, times(1)).save(eq(profile));
    }

    @Test
    public void testFindProfileById() {
        Integer idProfile = 1;
        Profile profile = Profile.builder().build();

        when(profileRepository.findById(idProfile)).thenReturn(Optional.of(profile));

        Profile result = profileService.findProfileById(idProfile);

        assertNotNull(result);
        assertEquals(profile, result);

        // Verifica se o método findById foi chamado no repositório
        verify(profileRepository, times(1)).findById(eq(idProfile));
    }

    @Test
    public void testFindProfileByIdNotFound() {
        Integer idProfile = 1;

        when(profileRepository.findById(idProfile)).thenReturn(Optional.empty());

        Profile result = profileService.findProfileById(idProfile);

        assertNull(result);

        // Verifica se o método findById foi chamado no repositório
        verify(profileRepository, times(1)).findById(eq(idProfile));
    }

    @Test
    public void testEditProfile() {
        Integer idProfile = 1;
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setUser(new User());
        Profile existingProfile = Profile.builder().build();

        when(profileRepository.findById(idProfile)).thenReturn(Optional.of(existingProfile));
        when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
        when(profileRepository.save(any())).thenReturn(existingProfile);

        Profile result = profileService.editProfile(profileRequest, idProfile);

        assertNotNull(result);
        assertEquals(existingProfile, result);

        // Verifica se o método findById e save foram chamados no repositório
        verify(profileRepository, times(1)).findById(eq(idProfile));
        verify(profileRepository, times(1)).save(eq(existingProfile));
    }

    @Test
    public void testEditProfileNotFound() {
        Integer idProfile = 1;
        ProfileRequest profileRequest = new ProfileRequest();

        when(profileRepository.findById(idProfile)).thenReturn(Optional.empty());

        Profile result = profileService.editProfile(profileRequest, idProfile);

        assertNull(result);

        // Verifica se o método findById não foi chamado no repositório
        verify(profileRepository, never()).findById(eq(idProfile));
        // Verifica se o método save não foi chamado no repositório
        verify(profileRepository, never()).save(any());
    }

    @Test
    public void testDeleteProfile() {
        Profile profile = Profile.builder().build();

        profileService.deleteProfile(profile);

        // Verifica se o método delete foi chamado no repositório
        verify(profileRepository, times(1)).delete(eq(profile));
    }
}

package com.ufmg.streaming.API.Streaming.profile;

import com.ufmg.streaming.API.Streaming.user.User;
import com.ufmg.streaming.API.Streaming.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository){
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Profile getProfileById(Integer idProfile){
        return profileRepository.findById(idProfile).orElse(null);
    }

    public void createProfile(ProfileRequest request) {
        var profile = Profile.builder()
                .user(request.getUser())
                .profile_name(request.getProfile_name())
                .is_child_profile(request.getIs_child_profile())
                .profile_icon(request.getProfile_icon())
                .build();
        profileRepository.save(profile);
    }

    public Profile findProfileById(Integer idProfile) {
        return profileRepository.findById(idProfile).orElse(null);
    }

    public Profile editProfile(ProfileRequest request, Integer idProfile) {
        Profile existingProfile = profileRepository.findById(idProfile).orElse(null);

        if(existingProfile == null){
            return null;
        }
        User user = userRepository.findById(request.getUser().getId()).orElse(null);
        existingProfile.setUser(user);
        existingProfile.setProfile_name(request.getProfile_name());
        existingProfile.setIs_child_profile(request.getIs_child_profile());
        existingProfile.setProfile_icon(request.getProfile_icon());

        return profileRepository.save(existingProfile);
    }
}

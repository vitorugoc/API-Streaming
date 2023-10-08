package com.ufmg.streaming.API.Streaming.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public Profile getProfileById(Integer idProfile){
        return profileRepository.findById(idProfile).orElse(null);
    }

}

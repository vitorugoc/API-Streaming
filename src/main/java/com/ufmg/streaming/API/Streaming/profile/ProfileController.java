package com.ufmg.streaming.API.Streaming.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @PostMapping()
    public ResponseEntity<String> createProfile(@RequestBody ProfileRequest request){
        profileService.createProfile(request);
        return ResponseEntity.ok("Perfil criado com sucesso!");
    }

    @GetMapping("/{idProfile}")
    public ResponseEntity<Profile> findProfileById(@PathVariable Integer idProfile){
        Profile profile = profileService.findProfileById(idProfile);
        if(profile == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }

    @PutMapping("/edit/{idProfile}")
    public ResponseEntity<Profile> editProfile(@PathVariable Integer idProfile, @RequestBody ProfileRequest request){
       Profile newProfile = profileService.editProfile(request, idProfile);

       if (newProfile == null){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(newProfile);
    }

    @DeleteMapping("/delete/{idProfile}")
    public  ResponseEntity<String> deleteProfile(@PathVariable Integer idProfile){
        Profile toDeleteProfile = profileService.findProfileById(idProfile);
        profileService.deleteProfile(toDeleteProfile);

        return ResponseEntity.ok("Perfil deletado com sucesso!");
    }
}

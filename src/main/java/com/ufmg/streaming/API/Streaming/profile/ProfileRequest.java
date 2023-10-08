package com.ufmg.streaming.API.Streaming.profile;

import com.ufmg.streaming.API.Streaming.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
    private User user;
    private String profile_name;
    private Integer is_child_profile;
    private String profile_icon;
}

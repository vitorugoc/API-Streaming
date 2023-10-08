package com.ufmg.streaming.API.Streaming.profile;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_profile")
public class Profile {
    @Id
    @GeneratedValue
    private Integer id_profile;
    private String profile_name;
    private Integer is_child_profile;
    private String profile_icon;


}

package com.ufmg.streaming.API.Streaming.profile;

import com.ufmg.streaming.API.Streaming.user.User;
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
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
    private String profile_name;
    private Integer is_child_profile;
    private String profile_icon;
}

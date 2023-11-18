package com.ufmg.streaming.API.Streaming.titles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TitleRequest {
    private Integer id_title;
    private String title_name;
    private String title_desc;
    private Integer is_allowed_for_children;
    private String title_image;
    private Integer title_rank;
}

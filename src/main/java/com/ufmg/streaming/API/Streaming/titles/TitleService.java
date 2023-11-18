package com.ufmg.streaming.API.Streaming.titles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleService {
    private final TitleRepository titleRepository;

    @Autowired
    public TitleService(TitleRepository titleRepository){
        this.titleRepository = titleRepository;
    }

    public List<Title> findAllTitles(){
        return titleRepository.findAll();
    }

    public void createTitle(TitleRequest request) {
        var title = Title.builder()
                .title_name(request.getTitle_name())
                .title_desc(request.getTitle_desc())
                .is_allowed_for_children(request.getIs_allowed_for_children())
                .title_image(request.getTitle_image())
                .title_rank(request.getTitle_rank())
                .build();
        titleRepository.save(title);
    }

    public Title findTitleById(Integer idTitle){
        return titleRepository.findById(idTitle).orElse(null);
    }

    public Title editTitle(TitleRequest request, Integer idTitle) {
        Title existingTitle = titleRepository.findById(idTitle).orElse(null);

        if(existingTitle == null){
            return null;
        }

        existingTitle.setTitle_name(request.getTitle_name());
        existingTitle.setTitle_desc(request.getTitle_desc());
        existingTitle.setIs_allowed_for_children(request.getIs_allowed_for_children());
        existingTitle.setTitle_image(request.getTitle_image());
        existingTitle.setTitle_rank(request.getTitle_rank());

        return titleRepository.save(existingTitle);
    }

    public void deleteTitle(Title title){ titleRepository.delete(title);}
}

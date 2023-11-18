package com.ufmg.streaming.API.Streaming.titles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/title")
public class TitleController {

    private final TitleService titleService;

    @Autowired
    public TitleController(TitleService titleService){
        this.titleService = titleService;
    }

    @PostMapping()
    public ResponseEntity<String> createTitle(@RequestBody TitleRequest request){
        titleService.createTitle(request);
        return ResponseEntity.ok("Título criado com sucesso!");
    }

    @GetMapping("/{idTitle}")
    public ResponseEntity<Title> findTitleById(@PathVariable Integer idTitle){
        Title title = titleService.findTitleById(idTitle);

        if(title == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(title);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Title>> findAllTitles(){
        List<Title> titles = titleService.findAllTitles();

        if(titles == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(titles);
    }

    @PutMapping("/edit/{idTitle}")
    public ResponseEntity<Title> editTitle(@PathVariable Integer idTitle, @RequestBody TitleRequest request){
        Title newTitle = titleService.editTitle(request, idTitle);

        if (newTitle == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(newTitle);
    }

    @DeleteMapping("/delete/{idTitle}")
    public  ResponseEntity<String> deleteTitle(@PathVariable Integer idTitle){
        Title toDeleteTitle = titleService.findTitleById(idTitle);
        titleService.deleteTitle(toDeleteTitle);

        return ResponseEntity.ok("Título deletado com sucesso!");
    }
}

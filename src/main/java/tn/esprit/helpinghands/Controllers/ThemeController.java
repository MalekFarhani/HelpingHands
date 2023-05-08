package tn.esprit.helpinghands.Controllers;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.helpinghands.entities.Theme;
import tn.esprit.helpinghands.serviceImpl.IThemeService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Theme")
public class ThemeController {

    @Autowired
    private final IThemeService themeService;
    @PostMapping("/add")
    public Theme add(@RequestBody Theme T){return themeService.addTheme(T);}
    @PutMapping("/update/{id}")
    Theme update(@RequestBody Theme T, @PathVariable Long id){return themeService.updateTheme(T,id);}


    @GetMapping(value = "/all")
    public List<Theme> getAll(){return themeService.getall();}

    @GetMapping("/get/{id}")
    public Theme getById(@PathVariable Long id){return (Theme) themeService.getById(id);}
    @DeleteMapping("/delete/{id}")
    public  void remove(@PathVariable Long id){ themeService.removeTheme(id);}




}






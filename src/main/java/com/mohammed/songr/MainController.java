package com.mohammed.songr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    @Autowired
    private AlbumRepository albumRepository;


    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    String getGreeting(@RequestParam(name="name", required = false, defaultValue = "World") String name , Model userName) {
        userName.addAttribute("name" , name);
        return "welcome";
    }

    @GetMapping("/capitalize/{word}")
    public String showCapital(Model m, @PathVariable("word") String word){
        m.addAttribute("word", word);
        return "capital";
    }

    Album albums[] = {
            new Album("sehert allail",
            "George wassouf" ,
                    15 ,
                    1500 , "https://layalina.awicdn.com/site-images/sites/default/files/prod/article/180918/%D8%B5%D9%88%D8%B1%D8%A9-%D8%AC%D9%88%D8%B1%D8%AC-%D9%88%D8%B3%D9%88%D9%81-%D8%A7%D9%84%D8%AA%D9%8A-%D8%A3%D8%AB%D8%A7%D8%B1%D8%AA-%D8%A7%D8%B3%D8%AA%D8%BA%D8%B1%D8%A7%D8%A8-%D8%AC%D9%85%D9%87%D9%88%D8%B1%D9%87-1348334.gif?preset=v4.0_770X577&save-png=1&rnd=0519151220214-OLD&animation=1")
            ,
            new Album("Love of my life"
                , "Queen",
                    26 ,
                    15002 ,
                    "https://www.nme.com/wp-content/uploads/2020/03/queen-bohemianrhapsody-2000x1270-1-696x442.jpg")
        ,
        new Album("Hello World"
        , "Mohammed Mohiesen" ,
                10 ,
                2600 ,
"https://www.rollingstone.com/wp-content/uploads/2018/06/rs-13220-johnnycash-624-1383147488.jpg?resize=1800,1200&w=1200"        )
    };

    @PostMapping("/albums")
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RedirectView addAlbum(@RequestParam(value = "title") String title ,
                                 @RequestParam(value= "artist") String artist,
                                 @RequestParam(value="songCount") int songCount,
                                 @RequestParam(value="imageUrl") String imageUrl,
                                 @RequestParam(value="length") long length) {
        try {
            Album album = new Album(title,artist,songCount,length,imageUrl);
            System.out.println(title);
            System.out.println(artist);
            System.out.println(songCount);
            System.out.println(length);
            albumRepository.save(album);
            return  new RedirectView("/albums");
        }

        catch (Exception e ) {
            return new RedirectView("/error");
        }

    }
    @GetMapping("/albums")
    String getAlbum(Model album) {
        album.addAttribute("album" , albumRepository.findAll());
        return "albums";
    }

    @GetMapping("/addalbum")
    String addAlbum() {
        return "addalbum";
    }


}

package com.mohammed.songr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class SongReq {
    @Autowired
    SongRepository songRepository;
    @Autowired
    AlbumRepository albumRepository;
    @RequestMapping(value = "/songs" , method = RequestMethod.GET)
    public String getSongs(Model song) {
        song.addAttribute("song" , songRepository.findAll());
        return "songs";
    }

    @RequestMapping(value = "/addsong" , method = RequestMethod.GET)
    public String addSongForm() {
        return "addsong";
    }

    @RequestMapping(value = "/songs" , method = RequestMethod.POST)
    public RedirectView addNewSong(
            @RequestParam Long album_id,
            @RequestParam String title,
            @RequestParam long length,
            @RequestParam int trackNumber
    ) {
        try {
            Album album = albumRepository.getById(album_id);
            Song newSong = new Song(album , title , length , trackNumber);
            songRepository.save(newSong);

            return new RedirectView("/songs");
        }

        catch (Exception e ) {
            System.out.println(e.getMessage());
            return new RedirectView("/error");
        }

    }
}

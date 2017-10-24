package ru.kakDela.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kakDela.notes.stuf.FilterTypes;
import ru.kakDela.notes.stuf.SortingTypes;

/**
 * Created by Михаил on 20.10.2017.
 */
@Controller
public class EditorController {

    private DataService service;
    @Autowired
    public void setService(DataService service) {
        this.service = service;
    }


    @GetMapping("/notes")
    @ResponseBody
    public NoteEntity getNote(@RequestParam(value="id", required=true) String id) {
        try{
            NoteEntity note = service.getById(Integer.parseInt(id));
            if(note != null) return note;
        }catch (NumberFormatException e ){
        } catch (Exception t){

        }
        return null;
    }
    @PostMapping("/update")
    @ResponseBody
    public String updateNote(@ModelAttribute NoteEntity note) {
        try{
           service.update(note);
            return "ok";
        } catch (Exception t){

        }
        return "ne ok";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value="id") String id){
            try{
                service.remove(Integer.parseInt(id));
                return "ok";
            }catch (Exception e ){
            }
        return "ne ok";
    }
    @PostMapping("/addNote")
    @ResponseBody
    public String addNote(@ModelAttribute NoteEntity note) {
        try{
            service.insert(note);
            return "ok";
        }catch (Exception e ){
        }
        return "ne ok";
    }
    @RequestMapping(value= {"/list","/"})
    public String listing(@RequestParam(value="page", required=false, defaultValue="0") String page,
                          @RequestParam(value="limit", required=false, defaultValue="10") String limit,
                          @RequestParam(value="sorted", required=false, defaultValue="ByDateDesc") String sortBy,
                          @RequestParam(value="filtered", required=false, defaultValue="ALL") String filterBy,
                          Model model) {
        SortingTypes sort = null;
        FilterTypes filter = null;
        try{
            sort = SortingTypes.valueOf(sortBy);
            filter = FilterTypes.valueOf(filterBy);
        }catch (IllegalArgumentException e){
            sort = SortingTypes.ByDateDesc;
            filter = FilterTypes.ALL;
        }
        int countOfPages = service.countOfPages(limit, filter);

        model.addAttribute("listOfNotes", service.find(sort,filter,page,limit));
        model.addAttribute("countOfPages", countOfPages ==0 ? 1: countOfPages);
        model.addAttribute("page", Integer.parseInt(page));
        model.addAttribute("limit", limit);
        model.addAttribute("sorted", sortBy);
        model.addAttribute("filtered", filterBy);
        model.addAttribute("note", new NoteEntity());
        return "listing";
    }


}

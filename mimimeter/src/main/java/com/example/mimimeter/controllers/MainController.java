package com.example.mimimeter.controllers;

import com.example.mimimeter.models.CatComporator;
import com.example.mimimeter.models.Cats;
import com.example.mimimeter.repos.Repos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;
import java.util.*;

@Controller
@SessionAttributes("catsM")
public class MainController {

    @Autowired
    private Repos repos;

    @GetMapping("/")
    public String main(HttpSession session){
        ArrayList<Long> catsM = new ArrayList<Long>();
        Iterable<Cats> cats = repos.findAll();
        for (var c : cats) {
            catsM.add(c.getId());
        }
        session.setAttribute("catsM", catsM);

        return "main";
    }

    @GetMapping("/start")
    public String start(Model model, HttpSession session) {

        ArrayList<Long> cats = getCats(session);

        if (cats.isEmpty()){
            return "redirect:/top";
        }

        Long id1 = cats.get(0);
        Long id2 = cats.get(1);
        Optional<Cats> cat1 = repos.findById(id1);
        Optional<Cats> cat2 = repos.findById(id2);
        model.addAttribute("cat1",cat1.get());
        model.addAttribute("cat2",cat2.get());

        return "start";
    }

    @GetMapping("/select/{id}")
    public String select(@PathVariable(value = "id") long id, Model model) {
        Cats cat = repos.findById(id).get();
        cat.setPosition(cat.getPosition()+1);
        repos.save(cat);

        return "redirect:/start";
    }

    @GetMapping("/top")
    public String top(Model model) {
        int i = 1;
        List<Cats> cats = (List<Cats>) repos.findAll();
        Collections.sort(cats, new CatComporator());
        model.addAttribute("cats", cats);

        return "top";
    }

    private ArrayList<Long> getCats(HttpSession session){
        ArrayList<Long> catsM = (ArrayList<Long>) session.getAttribute("catsM");

        ArrayList<Long> cats = new ArrayList<Long>();

        if (!catsM.isEmpty()) {
            long cat1 = catsM.get((int) Math.floor(Math.random() * catsM.size()));
            catsM.remove(cat1);
            long cat2 = catsM.get((int) Math.floor(Math.random() * catsM.size()));
            catsM.remove(cat2);
            cats.add(cat1);
            cats.add(cat2);
            session.setAttribute("catsM", catsM);
        }

        return cats;
    }

}

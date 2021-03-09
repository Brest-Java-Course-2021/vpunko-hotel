package com.epam.brest.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ApartmentsController {

    /**
     * Goto apartment list page.
     *
     * @return view name
     */
    @GetMapping(value = "/Apartments")
    public final String apartments(Model model) {
        return "Apartments";
    }

    /**
     * Goto edit apartment page.
     *
     * @return view name
     */
    @GetMapping(value = "/ApartmentPage/{id}")
    public final String gotoEditApartmentPage(@PathVariable Integer id, Model model) {
        return "ApartmentPage";
    }


    /**
     * Goto new apartment page.
     *
     * @return view name
     */
    @GetMapping(value = "/ApartmentPage/add")
    public final String gotoAddApartmentPage(Model model) {
        return "ApartmentPage";
    }
}

package com.epam.brest.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResidentController {

    /**
     * Goto resident list page.
     *
     * @return view name
     */
    @GetMapping(value = "/Residents")
    public final String residentsList(Model model) {
        return "Residents list";
    }

    /**
     * Goto edit resident page.
     *
     * @return view name
     */
    @GetMapping(value = "/Resident/{id}")
    public final String gotoEditResidentPage(@PathVariable Integer id, Model model) {
        return "Resident";
    }

    @GetMapping(value = "/Resident/add")
    public final String gotoAddResidentPage(Model model) {
        return "Resident";
    }

}

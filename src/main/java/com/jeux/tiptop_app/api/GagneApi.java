package com.jeux.tiptop_app.api;

import com.jeux.tiptop_app.service.GagneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kaoutarelmofatiche
 */
@RestController
public class GagneApi {

    @Autowired
    GagneService gagneService;

    @PostMapping("/gagne")
    public String gagne(@RequestParam("user") String user,@RequestParam("gagne") String gagne) {

        gagneService.savegagne(user,gagne);

        return "success";
    }


}

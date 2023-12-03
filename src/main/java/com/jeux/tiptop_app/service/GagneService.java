package com.jeux.tiptop_app.service;


import com.jeux.tiptop_app.entity.Gagne;

import java.util.List;


public interface GagneService {

    void savegagne(String user,String gagne);


    List<Gagne> getAllGagne();

    void etatGagne(Long client);

    List<Gagne> getByEmail(String email);
}

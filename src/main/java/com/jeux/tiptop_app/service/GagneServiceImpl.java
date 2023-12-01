package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.Gagne;
import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.repository.GagneRepository;
import com.jeux.tiptop_app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GagneServiceImpl implements GagneService {

    @Autowired
    GagneRepository gagneRepository;


    @Override
    public void savegagne(String user,String gagne) {
        Gagne ga = new Gagne();
        ga.setGagne(gagne);
        ga.setUser(user);
        ga.setEtat("0");
        gagneRepository.save(ga);
    }

    @Override
    public List<Gagne> getAllGagne() {
        return gagneRepository.findAll();
    }

    @Override
    public void etatGagne(Long client) {
        Gagne gagneById = gagneRepository.getOne(client);
        gagneById.setEtat("1");
        gagneRepository.save(gagneById);
    }

    @Override
    public List<Gagne> getByEmail(String email) {
        return gagneRepository.getByEmail(email);
    }


}

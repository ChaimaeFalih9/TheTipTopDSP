package com.jeux.tiptop_app.service;

import com.jeux.tiptop_app.entity.Gagne;
import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.repository.GagneRepository;
import com.jeux.tiptop_app.repository.GameRepository;
import com.jeux.tiptop_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GagneServiceImpl implements GagneService {

    @Autowired
    GagneRepository gagneRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void savegagne(String user,String gagne) {

       User u = userRepository.getByname(user);
        Gagne ga = new Gagne();
        ga.setGagne(gagne);
        ga.setUser(user);
        ga.setEtat("0");
        ga.setEmail(u.getEmail());
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

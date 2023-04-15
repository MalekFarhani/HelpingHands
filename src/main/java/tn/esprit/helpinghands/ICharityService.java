package tn.esprit.kaddemproject.services;

import tn.esprit.kaddemproject.entities.Charity;

import java.util.List;

public interface ICharityService {
    void add(Charity s);

    Charity update(Charity s);

    List<Charity> getAll();

    Charity getById(long id);

    void remove(long id);
}

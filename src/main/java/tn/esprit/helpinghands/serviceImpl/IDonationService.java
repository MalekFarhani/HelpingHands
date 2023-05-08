package tn.esprit.helpinghands.serviceImpl;

import tn.esprit.helpinghands.entities.Donation;

import javax.transaction.Transactional;
import java.util.List;

public interface IDonationService {

    Donation addDonation(Donation D);

    Donation updateDonation(Donation donation,Long id);

    List<Donation> getall();

    Donation getById(Long id);

    void removeDonation(Long id);


}

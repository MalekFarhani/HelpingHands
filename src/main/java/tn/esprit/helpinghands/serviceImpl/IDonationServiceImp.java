package tn.esprit.helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.Donation;
import tn.esprit.helpinghands.repositories.DonationRepository;

import javax.transaction.Transactional;
import java.util.List;
@Service

public class IDonationServiceImp implements IDonationService{
    @Autowired
    private DonationRepository donationRepository;
    @Override
    public Donation addDonation(Donation D) {
        donationRepository.save(D);
        return D;}
    @Override
    public Donation updateDonation(Donation donation,Long id){
        return donation;}
    @Override
    public List<Donation> getall(){
        return  donationRepository.findAll();
    }
    @Override
    public Donation getById(Long id){
        return  donationRepository.findById(id).orElse(null);
    }
    @Override
    public void removeDonation(Long id) {
        donationRepository.deleteById(id);}


}

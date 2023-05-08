package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.helpinghands.entities.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Long> {

}

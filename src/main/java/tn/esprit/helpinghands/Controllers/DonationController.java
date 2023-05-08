package tn.esprit.helpinghands.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.helpinghands.entities.Donation;
import tn.esprit.helpinghands.serviceImpl.IDonationService;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
@RequestMapping("/Donation")
public class DonationController {


    @Autowired
    private IDonationService donationService;
    @PostMapping("/add")
    public Donation add(@RequestBody Donation D){return donationService.addDonation(D);}
    @PutMapping("/update/{id}")
    Donation update(@RequestBody Donation d,@PathVariable Long id){return donationService.updateDonation(d,id);}


    @GetMapping(value = "/getAll")
    public List<Donation> getAll(){return donationService.getall();}

    @GetMapping("/get/{id}")
    public Donation getById(@PathVariable Long id){return (Donation) donationService.getById(id);}
    @DeleteMapping("/delete/{id}")
    public  void remove(@PathVariable Long id){ donationService.removeDonation(id);}}

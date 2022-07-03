package in.bushansirgur.springbootthymeleaf.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.env.spi.AnsiSqlKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.bushansirgur.springbootthymeleaf.dao.MedecinRepository;
import in.bushansirgur.springbootthymeleaf.dao.PatientRepository;
import in.bushansirgur.springbootthymeleaf.dao.SecretaireRepository;
import in.bushansirgur.springbootthymeleaf.dao.SituationFinanciereRepository;
import in.bushansirgur.springbootthymeleaf.dao.RdvRepository;
import in.bushansirgur.springbootthymeleaf.entity.Secretaire;
import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.Rdv;
import in.bushansirgur.springbootthymeleaf.entity.SituationFinanciere;

@Controller
public class SecretaireController {
	
	@Autowired
	private MedecinRepository mRepo;

    @Autowired
	private SecretaireRepository sRepo;


    @Autowired
	private PatientRepository pRepo;
    
    @Autowired
	private SituationFinanciereRepository sfRepo;

    @Autowired
	private RdvRepository rdvRepo;



    @PostMapping("/UpdateSecretaire")
    public String saveOrUpdateProfile(@ModelAttribute Secretaire sec,RedirectAttributes redirectAttributes) {
        sRepo.UpdateSecretaire(sec.getPasswordPersonneUser());
        return "redirect:/SecretaireProfile";
    }

    @GetMapping("/SecretaireProfile")
    public ModelAndView profile(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Secretaire/index");
    	Secretaire sec = new Secretaire();
        sec = sRepo.findBynomPersonne("fatiha");
        mav.addObject("sec", sec);
        return mav;
    }

    
    //Patients Page
    @GetMapping("/patientsSecretaire")
    public ModelAndView patients(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Secretaire/patients");
        List<Patient> patients = pRepo.findAll();
        Patient patient = new Patient();
        mav.addObject("patients", patients);
        mav.addObject("patient", patient);
        return mav;
    }

    @PostMapping("/secretaire/AddPatient")
    public String saveOrUpdatePatient(@ModelAttribute Patient patient,RedirectAttributes redirectAttributes) {
        pRepo.save(patient);
        SituationFinanciere sf = new SituationFinanciere();
        LocalDate localDate = LocalDate.now();
        sf.setDateSf(localDate);
        sf.setTotal(0.00);
        sf.setMtPaye(0.00);
        sf.setRestPaye(0.00);
		sf.setPatient(patient);
		sfRepo.save(sf);
        return "redirect:/patientsSecretaire";
    }

    //Facture Page
    @GetMapping("/facturesSecretaire")
    public ModelAndView factures(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Secretaire/invoices-list");
        List<SituationFinanciere> sf = mRepo.findPatientsSfs();

        mav.addObject("sf", sf);
        return mav;
    }


    //Sf Patient
    @GetMapping("/PatientSf")
    public ModelAndView GetPatientSf(@RequestParam(value="pat") String email,RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Secretaire/billing");
        Patient newPat = pRepo.findByEmailPersonne(email);
        Patient newPat1 = sfRepo.findByPatient(newPat).get(1);
        System.out.println(newPat1 .toString());
    	List<SituationFinanciere> sf = mRepo.findPatientSf(newPat);
        System.out.println(sf.toString());
        SituationFinanciere sit = sf.get(1);
        SituationFinanciere newSit = new SituationFinanciere();
        mav.addObject("sf", sf);
        mav.addObject("newSit", newSit);
        mav.addObject("patient", newPat1);
        return mav;
    }

    //add facture
    @PostMapping("/AddFacture")
    public String saveOrUpdateOrdo(@RequestParam(value="pat") String email,@ModelAttribute SituationFinanciere newSit,RedirectAttributes redirectAttributes) {
        LocalDate localDate = LocalDate.now();
        Patient newPat = pRepo.findByEmailPersonne(email);
        Patient newPat1 = sfRepo.findByPatient(newPat).get(1);
        newSit.setDateSf(localDate);
        newSit.setRestPaye(newSit.getTotal()-newSit.getMtPaye());
        System.out.println(newSit.toString());
        System.out.println(newPat1.toString());
        newSit.setPatient(newPat);
        sfRepo.save(newSit);
        redirectAttributes.addAttribute("pat", newPat.getEmailPersonne());
        return "redirect:/PatientSf";
    }

    //Liste des rendez-vous
    @GetMapping("/SecretaireRdvs")
	public ModelAndView getAllRdvs() {
		ModelAndView mav = new ModelAndView("Secretaire/RendezVous");
		List<Patient> patients = pRepo.findAll();
		mav.addObject("patient", patients);
		return mav;
	}

	@GetMapping({"SecretaireDeleteRdv"})
	public String DeleteRdv(@RequestParam(value="id") Integer RdvId,RedirectAttributes redirectAttributes) {
		 Rdv rdv = rdvRepo.findById(RdvId).get();
		 rdv.setStatus("supprimé");
		 rdvRepo.save(rdv);
		 return "redirect:/SecretaireRdvs";
	}

    @GetMapping({"SecretaireValiderRdv"})
	public String ValiderRdv(@RequestParam(value="id") Integer RdvId,RedirectAttributes redirectAttributes) {
		 Rdv rdv = rdvRepo.findById(RdvId).get();
		 rdv.setStatus("validé");
		 rdvRepo.save(rdv);
		 return "redirect:/SecretaireRdvs";
	}
}

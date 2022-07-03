package in.bushansirgur.springbootthymeleaf.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import in.bushansirgur.springbootthymeleaf.dao.PatientRepository;
import in.bushansirgur.springbootthymeleaf.dao.MedecinRepository;
import in.bushansirgur.springbootthymeleaf.dao.CertificatMedRepository;
import in.bushansirgur.springbootthymeleaf.dao.OrdonnanceRepository;
import in.bushansirgur.springbootthymeleaf.dao.ActeRepository;
import in.bushansirgur.springbootthymeleaf.entity.Medecin;
import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.Acte;
import in.bushansirgur.springbootthymeleaf.entity.Ordonnance;
import in.bushansirgur.springbootthymeleaf.entity.CertificatMed;

import in.bushansirgur.springbootthymeleaf.dao.SituationFinanciereRepository;
import in.bushansirgur.springbootthymeleaf.entity.SituationFinanciere;

@Controller
public class AdminController {
	
	@Autowired
	private MedecinRepository mRepo;

    @Autowired
	private ActeRepository aRepo;

    @Autowired
	private PatientRepository pRepo;

    @Autowired
	private CertificatMedRepository certifRepo;

    @Autowired
	private OrdonnanceRepository ordoRepo;

    @Autowired
	private SituationFinanciereRepository sfRepo;



    @GetMapping("/admin")
	public ModelAndView getAdmin() {
		ModelAndView mav = new ModelAndView("Admin/index");
		List<Patient> pat =mRepo.findPatientRdv();
		mav.addObject("patient",pat);
		Double total = mRepo.findMtTotal();
        
        mav.addObject("total", total);
		
		return mav;
	}

    @GetMapping("/actes")
	public ModelAndView getActes() {
		ModelAndView mav = new ModelAndView("Admin/actes");
		List<Acte> actes =aRepo.findAll();
		mav.addObject("acte",actes);
		//mav.addObject("rdvs", SecList);
		return mav;
	}

    @GetMapping("/actes/DeleteActe")
    public String deleteActe(@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
    	aRepo.deleteById(id);
        return "redirect:/actes";
    }

    @PostMapping("/actes/AddActe")
    public String saveOrUpdateActe(@ModelAttribute Acte acte,RedirectAttributes redirectAttributes) {
        aRepo.save(acte);
        return "redirect:/profile";
    }

    @PostMapping("/UpdateProfile")
    public String saveOrUpdateProfile(@ModelAttribute Medecin med,RedirectAttributes redirectAttributes) {
        mRepo.UpdateMedecin(med.getPasswordPersonneUser());
        return "redirect:/profile";
    }

    //Patients Page
    @GetMapping("/patientsAdmin")
    public ModelAndView patients(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Admin/patients");
        List<Patient> patients = pRepo.findAll();
        Patient patient = new Patient();
        mav.addObject("patients", patients);
        mav.addObject("patient", patient);
        return mav;
    }

    @PostMapping("/admin/AddPatient")
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
        return "redirect:/patientsAdmin";
    }

    @GetMapping("/admin/DeletePatient")
    public String deletePatient(@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
    	pRepo.deleteById(id);
        return "redirect:/patientsAdmin";
    }

    //Certifs Page
    @GetMapping("/certifsAdmin")
    public ModelAndView certifs(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Admin/certifs");
        List<CertificatMed> certifs = certifRepo.findAll();
        mav.addObject("certifs", certifs);
        return mav;
    }

    @GetMapping("/adminAddCertif")
    public ModelAndView getCertif(RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("Admin/AddCertif");
        CertificatMed certificat = new CertificatMed();
        mav.addObject("certif", certificat);
        return mav;
    }

    @PostMapping("/admin/AddCertif")
    public String saveOrUpdateCertif(@ModelAttribute CertificatMed certif,RedirectAttributes redirectAttributes) {
        certifRepo.save(certif);
        return "redirect:/certifsAdmin";
    }

    @GetMapping("/admin/DeleteCertif")
    public String deleteCertif(@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
    	certifRepo.deleteById(id);
        return "redirect:/certifsAdmin";
    }

    //Ordo Page
    @GetMapping("/ordoAdmin")
    public ModelAndView ordonnances(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Admin/ordonnances");
        List<Ordonnance> ordo = ordoRepo.findAll();
        mav.addObject("ordo", ordo);
        return mav;
    }

    @GetMapping("/adminAddOrdo")
    public ModelAndView getOrdo(RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("Admin/AddOrdo");
        Ordonnance ordo = new Ordonnance();
        mav.addObject("ordo", ordo);
        return mav;
    }

    @PostMapping("/admin/AddOrdo")
    public String saveOrUpdateOrdo(@ModelAttribute Ordonnance ordo,RedirectAttributes redirectAttributes) {
        ordoRepo.save(ordo);
        return "redirect:/ordoAdmin";
    }

    @GetMapping("/admin/DeleteOrdo")
    public String deleteOrdo(@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
    	ordoRepo.deleteById(id);
        return "redirect:/ordoAdmin";
    }

    //Facture Page
    @GetMapping("/facturesAdmin")
    public ModelAndView factures(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Admin/invoices-list");
        List<SituationFinanciere> sf = mRepo.findPatientsSfs();

        mav.addObject("sf", sf);
        return mav;
    }

    /*@PostMapping("/admin/AddPatient")
    public String saveOrUpdatePatient(@ModelAttribute Patient patient,RedirectAttributes redirectAttributes) {
        pRepo.save(patient);
        return "redirect:/patientsAdmin";
    }

    @GetMapping("/admin/DeletePatient")
    public String deletePatient(@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
    	pRepo.deleteById(id);
        return "redirect:/patientsAdmin";
    }*/

    @GetMapping("/profile")
    public ModelAndView profile(RedirectAttributes redirectAttributes) {
    	ModelAndView mav = new ModelAndView("Admin/contacts-profile");
    	Acte acte = new Acte();
    	Medecin med = new Medecin();
        med = mRepo.findBynomPersonne("soso");
        mav.addObject("acte", acte);
        mav.addObject("med", med);
        return mav;
    }
}

package in.bushansirgur.springbootthymeleaf.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.bushansirgur.springbootthymeleaf.dao.PatientRepository;
import in.bushansirgur.springbootthymeleaf.entity.Patient;

import in.bushansirgur.springbootthymeleaf.dao.SecretaireRepository;
import in.bushansirgur.springbootthymeleaf.entity.Secretaire;

import in.bushansirgur.springbootthymeleaf.dao.MedecinRepository;
import in.bushansirgur.springbootthymeleaf.entity.Medecin;

import in.bushansirgur.springbootthymeleaf.dao.SituationFinanciereRepository;
import in.bushansirgur.springbootthymeleaf.entity.SituationFinanciere;

@Controller
public class LoginController {
	
	@Autowired
	private PatientRepository pRepo;

    @Autowired
	private SecretaireRepository sRepo;

    @Autowired
	private MedecinRepository mRepo;

	@Autowired
	private SituationFinanciereRepository sfRepo;
	
	@GetMapping("/")
	public ModelAndView Home() {
		ModelAndView mav = new ModelAndView("Login/index");
        Patient newPatient = new Patient();
		mav.addObject("patient", newPatient);
		return mav;
	}

    @PostMapping("/Login")
	public String Login(@ModelAttribute Patient patient,RedirectAttributes redirectAttributes) {
        if(pRepo.findByEmailPersonne(patient.getEmailPersonne()) != null
        && pRepo.findByPasswordPersonneUser(patient.getPasswordPersonneUser()) != null){
            Patient newPatient = pRepo.findByEmailPersonne(patient.getEmailPersonne());
            redirectAttributes.addAttribute("id", newPatient.getNumPersonne());
            return "redirect:/patients";
        }
        else if(sRepo.findByEmailPersonne(patient.getEmailPersonne()) != null
        && sRepo.findByPasswordPersonneUser(patient.getPasswordPersonneUser()) != null){
            Secretaire newSecretaire = sRepo.findByEmailPersonne(patient.getEmailPersonne());
            redirectAttributes.addAttribute("id", newSecretaire.getNumPersonne());
            return "redirect:/secretaire";
        }
        else if(mRepo.findByEmailPersonne(patient.getEmailPersonne()) != null
        && mRepo.findByPasswordPersonneUser(patient.getPasswordPersonneUser()) != null){
            Medecin newMedecin = mRepo.findByEmailPersonne(patient.getEmailPersonne());
            redirectAttributes.addAttribute("id", newMedecin.getNumPersonne());
            return "redirect:/admin";
        }
		return "redirect:/Login";
        
	}  


	@GetMapping({"/SignUp"})
	public ModelAndView SignUp() {
		ModelAndView mav = new ModelAndView("Login/SignUp");
        Patient newPatient = new Patient();
		mav.addObject("patient", newPatient);
		return mav;
	}
    
    @PostMapping({"/savePatient"})
	public String savePatient(@ModelAttribute Patient patient,RedirectAttributes redirectAttributes) {
		pRepo.save(patient);
        SituationFinanciere sf = new SituationFinanciere();
        LocalDate localDate = LocalDate.now();
        sf.setDateSf(localDate);
        sf.setTotal(0.00);
        sf.setMtPaye(0.00);
        sf.setRestPaye(0.00);
		sf.setPatient(patient);
		sfRepo.save(sf);
        redirectAttributes.addAttribute("id", patient.getNumPersonne());
		return "redirect:/patients";
	}
	
    

    
	
	/*@GetMapping({"/patient"})
	public ModelAndView patient() {
		ModelAndView mav = new ModelAndView("Patient/RendezVous");
		return mav;
	}
	@GetMapping({"/admin"})
	public ModelAndView admin() {
		ModelAndView mav = new ModelAndView("Admin/index");
		return mav;
	}
	@GetMapping({"/secretaire"})
	public ModelAndView secretaire() {
		ModelAndView mav = new ModelAndView("Secretaire/index");
		return mav;
	}
	/*@GetMapping({"/list"})
	public ModelAndView getAllEmployees() {
		ModelAndView mav = new ModelAndView("admin/list-employees");
		mav.addObject("employees", pRepo.findAll());
		return mav;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav = new ModelAndView("admin/add-employee-form");
		Employee newEmployee = new Employee();
		mav.addObject("employee", newEmployee);
		return mav;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee employee) {
		eRepo.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
		ModelAndView mav = new ModelAndView("add-employee-form");
		Employee employee = eRepo.findById(employeeId).get();
		mav.addObject("employee", employee);
		return mav;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list";
	}*/
    
    
}

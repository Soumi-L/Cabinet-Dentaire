package in.bushansirgur.springbootthymeleaf.controller;

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
import in.bushansirgur.springbootthymeleaf.dao.RdvRepository;
import in.bushansirgur.springbootthymeleaf.entity.Patient;
import in.bushansirgur.springbootthymeleaf.entity.Rdv;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository pRepo;
	
	@Autowired
	private RdvRepository rdvRepo;


	@GetMapping({"/dossier"})
	public ModelAndView dossierMedical(@RequestParam(value="id") Integer id) {
		ModelAndView mav = new ModelAndView("Patient/Dossier");
		Patient patient = pRepo.findById(id).get();
		mav.addObject("patient", patient);
		mav.addObject("id", id);
		return mav;
	}


	@GetMapping({"/updateRdv"})
	public ModelAndView updateRdv(@RequestParam Integer idRdv) {
		ModelAndView mav = new ModelAndView("Patient/RendezVous");
		Rdv rdv = rdvRepo.findById(idRdv).get();
         	mav.addObject("rdv", rdv);
		//mav.addObject("id", id);
		return mav;
	}
	
	@PostMapping("/saveRdv")
	public String saveRdv(@ModelAttribute Rdv rdv,@RequestParam (value="id") Integer id,RedirectAttributes redirectAttributes) {
		rdvRepo.save(rdv);
		Patient p = new Patient();
		List<Rdv> rdvs= pRepo.findByRdvs(id);
		if(rdvs.contains(rdv)){

		}else{
			rdv.setStatus("EnAttente");
			rdvs.add(rdv);
		}
		
		p=pRepo.findById(id).get();
		p.setRdvs(rdvs);
		pRepo.save(p);
		
		redirectAttributes.addAttribute("id", id);
		return "redirect:/patients";
	}
	
	@GetMapping({"/patient/DeleteRdv"})
	public String DeletPat(@RequestParam Integer RdvId,@RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
		 Rdv rdv = rdvRepo.findById(RdvId).get();
		 rdv.setStatus("supprimé");
		 rdvRepo.save(rdv);
		 redirectAttributes.addAttribute("id", id);
		 return "redirect:/patients";
	}

	@GetMapping("/patients")
	public ModelAndView getAllPatients(@RequestParam(value="id") Integer id) {
		ModelAndView mav = new ModelAndView("Patient/RendezVous");
		List<Rdv> rdvs = pRepo.findByRdvs(id);
		List<Rdv> targetLongList = rdvs.stream()
			    .filter(l -> l.getStatus().contains("EnAttente"))
			    .collect(Collectors.toList());
		Rdv rdv = new Rdv();
		if(rdvs.isEmpty()){
		
		}else if (targetLongList != null){
			mav.addObject("response","Data");
			mav.addObject("rdvs", targetLongList);
		}
		
		mav.addObject("id", pRepo.findId(id));
		mav.addObject("rdv", rdv);
		return mav;
	}

	@PostMapping("/updatePatient")
	public String savePatient(@ModelAttribute Patient patient,RedirectAttributes redirectAttributes) {
		pRepo.save(patient);
		redirectAttributes.addAttribute("id", patient.getNumPersonne());
		return "redirect:/patients";
	}

	@GetMapping({"/updatePatient"})
	public ModelAndView updatePatient(@RequestParam(value="id") Integer id) {
		ModelAndView mav = new ModelAndView("Patient/index");
		Patient patient = pRepo.findById(id).get();
		mav.addObject("patient", patient);
		mav.addObject("id", id);
		return mav;
	}
	
}

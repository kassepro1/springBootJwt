package sn.security.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.vote.ConsensusBased;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import sn.security.dao.ConsultationRepository;
import sn.security.dao.DossierMedicalRepository;
import sn.security.dao.PatientRepository;
import sn.security.dao.PosteRepository;
import sn.security.dao.RoleRepository;
import sn.security.dao.ServicesRepository;
import sn.security.entities.AppRole;
import sn.security.entities.AppUser;
import sn.security.entities.Consultation;
import sn.security.entities.DossierMedical;
import sn.security.entities.Patient;
import sn.security.entities.Poste;
import sn.security.entities.Service;
import sn.security.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private ServicesRepository sericeRepository;
	@Autowired
	private PosteRepository posteRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DossierMedicalRepository dossierMedicalRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm user) {
		AppUser appUser = new AppUser();
		System.out.println("Password "+user.getPassword());
		if(!(user.getPassword().equals(user.getRepassword()))) throw new RuntimeException("password must be equal");
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		appUser.setNom(user.getNom());
		appUser.setPrenom(user.getPrenom());
		appUser.setEmail(user.getEmail());
		appUser.setActived(true);
		appUser.setPoste(user.getPoste().get(0));
	//	appUser.setRoles(user.getRoles());
		appUser.setService(user.getService().get(0));
		AppUser userExist= accountService.findUtilisateurByUserName(user.getUsername());
		if(userExist!=null) throw new RuntimeException("user is already exist");
		accountService.addUser(appUser);
		for(int i=0;i<user.getRoles().size();i++) {
			System.out.println(user.getRoles().get(i).getId());
			accountService.addRoleToUser(appUser.getUsername(),user.getRoles().get(i).getId());	
		}
		
		return appUser;
	}
	@GetMapping("/services")
	public List<Service> getServices(){
		List<Service> lservices = sericeRepository.findAll();
		if(lservices != null) {
			return lservices;
		}
			
		return null;
	}
	@GetMapping("/postes")
	public List<Poste> getPostes(){
		List<Poste> lPoste = posteRepository.findAll();
		if(lPoste != null) {
			return lPoste;
		}
			
		return null;
	}
	@GetMapping("/roles")
	public List<AppRole> getRoles(){
		List<AppRole> lRole = roleRepository.findAll();
		if(lRole != null) {
			return lRole;
		}
			
		return null;
	}
	@GetMapping("/consultations")
	public List<Consultation> getConsultations(){
		List<Consultation> lConsultation = consultationRepository.findAll();
		if(lConsultation != null) {
			return lConsultation;
		}
			
		return null;
	}
	@GetMapping("/consultation/{id}")
	public Consultation getConsultations(@PathVariable("id") Long id){
		Consultation consultation = consultationRepository.findConsultationById(id);
		if(consultation != null) {
			return consultation;
		}
			
		return null;
	}
	
	@PostMapping("/addPatientOrConsultation")
	public int addPatientOrConsultation(@RequestBody PatientConsulationDTO porCons) {
		int added=0;
		DossierMedical dos = null;
		dos = dossierMedicalRepository.findDossierMedicalBynumDossier(porCons.getNumDossier());
		Consultation c = new Consultation();
		if(dos != null) {
			c.setCommentaire(porCons.getCommentaire());
			c.setDateCons(porCons.getDateConsultation());
			c.setPrescription(porCons.getPrescription());
			c.setService(porCons.getService().get(0));
			c.setUtilisateur(porCons.getUser());
			c.setPatient(dos.getPatient());
			c.setDossierMedical(dos);
			//Insertion Consultation 
			consultationRepository.save(c);
		} else{
			dos = new DossierMedical();
			Patient p = new Patient();
			p.setNom(porCons.getNom());
			p.setPrenom(porCons.getPrenom());
			p.setTel(porCons.getTel());
			p.setDatenais(porCons.getDateNaiss());
			p.setAdresse(porCons.getAdresse());
			p.setAge(porCons.getAge());
			String numDossier = generateNumDossier(porCons.getNom(), porCons.getPrenom(),1);
			p.setNumerodossier(numDossier);
			//Insertion Patient
			patientRepository.save(p);
			dos.setNumDossier(numDossier);
			dos.setDateConsultation(porCons.getDateConsultation());
			dos.setPatient(p);
			//Insertion Dossier
			dossierMedicalRepository.save(dos);
			c.setCommentaire(porCons.getCommentaire());
			c.setDateCons(porCons.getDateConsultation());
			c.setPrescription(porCons.getPrescription());
			c.setService(porCons.getService().get(0));
			c.setUtilisateur(porCons.getUser());
			c.setPatient(dos.getPatient());
			c.setDossierMedical(dos);
			//Insertion Consultation 
			consultationRepository.save(c);
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream("C:/Users/User/Desktop/iTextHelloWorld.pdf"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			// Chunk chunk = new Chunk("***************Ordonnance******************* ", font);
			Chunk or = new Chunk("Ordonnance", font);
			Chunk n = new Chunk("\n Nom \t: "+p.getNom() , font);
			Chunk pr = new Chunk("\n Prenom \t: "+p.getPrenom() , font);
			Chunk age = new Chunk("\n Age \t: "+p.getAge() , font);
			Chunk ob = new Chunk("\n Observation \t: "+c.getCommentaire() , font);
			Chunk pre = new Chunk("\n Nom \t: "+c.getPrescription() , font);
			Paragraph pn = new Paragraph(n);
			Paragraph ppr = new Paragraph(pr);
			Paragraph page = new Paragraph(age);
			Paragraph pob = new Paragraph(ob);
			Paragraph ppre = new Paragraph(pre);
			try {
				//document.add(chunk);
				document.add(or);
				document.add(pn);
				document.add(ppr);
				document.add(age);
				document.add(pob);
				document.add(ppre);
				//document.add(pre);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
		}
		return added;
	}
	
	public String generateNumDossier(String nom,String prenom,int idDos) {
		String initial=nom.substring(0,1)+prenom.substring(0,1);
		String seq="0000000";
		String num=seq.substring(idDos+"".length());
		int jour=LocalDate.now().getDayOfYear();
		int mois=LocalDate.now().getMonthValue();
		int an=LocalDate.now().getYear();
		return initial+num+idDos+""+jour+""+mois+""+an;
	}
	@CrossOrigin("*")
	@PostMapping("/editCons")
	public Consultation editCons(@RequestBody ConsDTO cdto) {
		Consultation c = new Consultation();
		if(cdto!=null) {
			c.setId(cdto.getId());
			c.setCommentaire(cdto.getCommentaire());
			c.setPrescription(cdto.getPrescription());
		return consultationRepository.save(c); 	
		}
		
		return null;
	}
	
	@GetMapping("/consByPatient/{idP}")
	public List<Consultation> getConsultationsByPatient(@PathVariable("idP") Long idP){
		return consultationRepository.getConsultationsByPatient(idP);
	}
	@GetMapping("/consByDay/{day}")
	public List<Consultation> getConsultationsByDay(@PathVariable("day") String date){
		
		 try {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				Date day = format.parse (date);
			System.out.println(day);
			return consultationRepository.getConsultationsByDay(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/historiquePatient/{numDossier}")
	public  Collection<Consultation> getHistoriquePatient(@PathVariable("numDossier") String numDossier){
		return dossierMedicalRepository.findDossierMedicalBynumDossier(numDossier).getConsultations();
	}
	@GetMapping("/stats/{dateOrService}")
	public  List<Stat> getStatsByJourOrService(@PathVariable("dateOrService") String dateOrService){
		System.out.println(dateOrService);
		//return dossierMedicalRepository.findDossierMedicalBynumDossier(numDossier).getConsultations();
		if(dateOrService.equalsIgnoreCase("jour")==true) {
		return consultationRepository.getNbConsultationByDay();
		}else if (dateOrService.equalsIgnoreCase("service")==true) {
			//Long idS=Long.parseLong(dateOrService);
			//return consultationRepository.getConsultationByService();
		}
		return null;
	}


}

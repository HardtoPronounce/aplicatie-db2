package ro.proiectdb2.aplicatie_db2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class HomeController {

    private final PacientRepository pacientRepository;
    private final MedicamentRepository medicamentRepository;
    private final RetetaRepository retetaRepository;

    public HomeController(PacientRepository pacientRepository,
                          MedicamentRepository medicamentRepository,
                          RetetaRepository retetaRepository,
                          javax.sql.DataSource dataSource) {
        this.pacientRepository = pacientRepository;
        this.medicamentRepository = medicamentRepository;
        this.retetaRepository = retetaRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pacientiCount", pacientRepository.count());
        model.addAttribute("medicamenteCount", medicamentRepository.count());
        model.addAttribute("reteteCount", retetaRepository.count());
        return "home";
    }

    @GetMapping("/pacienti")
    public String listPacienti(Model model) {
        model.addAttribute("pacienti", pacientRepository.findAll());
        return "pacienti";
    }

    @GetMapping("/pacienti/new")
    public String newPacientForm(Model model) {
        model.addAttribute("pacient", new Pacient());
        return "pacient-new";
    }

    @PostMapping("/pacienti/new")
    public String createPacient(@ModelAttribute Pacient pacient) {
        pacientRepository.save(pacient);
        return "redirect:/pacienti";
    }

    @GetMapping("/pacienti/edit/{id}")
    public String editPacientForm(@PathVariable Integer id, Model model) {
        Optional<Pacient> p = pacientRepository.findById(id);
        if (p.isEmpty()) return "redirect:/pacienti";
        model.addAttribute("pacient", p.get());
        return "pacient-edit";
    }

    @PostMapping("/pacienti/edit/{id}")
    public String updatePacient(@PathVariable Integer id, @ModelAttribute Pacient pacient) {
        pacient.setId_pacient(id);
        pacientRepository.save(pacient);
        return "redirect:/pacienti";
    }

    @PostMapping("/pacienti/delete/{id}")
    public String deletePacient(@PathVariable Integer id) {
        pacientRepository.deleteById(id);
        return "redirect:/pacienti";
    }

    @GetMapping("/medicamente")
    public String listMedicamente(Model model) {
        model.addAttribute("medicamente", medicamentRepository.findAll());
        return "medicamente";
    }

    @GetMapping("/medicamente/new")
    public String newMedicamentForm(Model model) {
        model.addAttribute("medicament", new Medicament());
        return "medicament-new";
    }

    @PostMapping("/medicamente/new")
    public String createMedicament(@ModelAttribute Medicament medicament) {
        medicamentRepository.save(medicament);
        return "redirect:/medicamente";
    }

    @GetMapping("/medicamente/edit/{id}")
    public String editMedicamentForm(@PathVariable Integer id, Model model) {
        Optional<Medicament> m = medicamentRepository.findById(id);
        if (m.isEmpty()) return "redirect:/medicamente";
        model.addAttribute("medicament", m.get());
        return "medicament-edit";
    }

    @PostMapping("/medicamente/edit/{id}")
    public String updateMedicament(@PathVariable Integer id, @ModelAttribute Medicament medicament) {
        medicament.setId_medicament(id);
        medicamentRepository.save(medicament);
        return "redirect:/medicamente";
    }

    @PostMapping("/medicamente/delete/{id}")
    public String deleteMedicament(@PathVariable Integer id) {
        medicamentRepository.deleteById(id);
        return "redirect:/medicamente";
    }

    @GetMapping("/retete")
    public String listRetete(Model model) {
        var all = retetaRepository.findAll();
        java.util.List<RetetaView> views = new java.util.ArrayList<>();
        for (Reteta r : all) {
            String pacientName = pacientRepository.findById(r.getId_pacient())
                .map(p -> p.getNume() + " " + p.getPrenume()).orElse("Unknown pacient");
            String medicName = medicamentRepository.findById(r.getId_medicament())
                .map(Medicament::getDenumire).orElse("Unknown medicament");
            views.add(new RetetaView(r.getId_pacient(), r.getId_medicament(), r.getDoza(), r.getData_start(), pacientName, medicName));
        }
        model.addAttribute("retete", views);
        return "retete";
    }

    @GetMapping("/retete/new")
    public String newRetetaForm(Model model) {
        model.addAttribute("reteta", new Reteta());
        model.addAttribute("pacienti", pacientRepository.findAll());
        model.addAttribute("medicamente", medicamentRepository.findAll());
        return "reteta-new";
    }

    @PostMapping("/retete/new")
    public String createReteta(@RequestParam Integer id_pacient,
                               @RequestParam Integer id_medicament,
                               @RequestParam String doza,
                               @RequestParam(required = false) String data_start) {
        Reteta reteta = new Reteta();
        reteta.setId_pacient(id_pacient);
        reteta.setId_medicament(id_medicament);
        reteta.setDoza(doza);
        if (data_start != null && !data_start.isBlank()) {
            reteta.setData_start(LocalDate.parse(data_start));
        }
        retetaRepository.save(reteta);
        return "redirect:/retete";
    }

    @GetMapping("/retete/edit/{pacientId}/{medicamentId}")
    public String editRetetaForm(@PathVariable Integer pacientId, @PathVariable Integer medicamentId, Model model) {
        RetetaId id = new RetetaId(pacientId, medicamentId);
        Optional<Reteta> r = retetaRepository.findById(id);
        if (r.isEmpty()) return "redirect:/retete";
        model.addAttribute("reteta", r.get());
        return "reteta-edit";
    }

    @PostMapping("/retete/edit/{pacientId}/{medicamentId}")
    public String updateReteta(@PathVariable Integer pacientId, @PathVariable Integer medicamentId,
                               @RequestParam String doza, @RequestParam(required = false) String data_start) {
        RetetaId id = new RetetaId(pacientId, medicamentId);
        Reteta reteta = retetaRepository.findById(id).orElse(new Reteta());
        reteta.setId_pacient(pacientId);
        reteta.setId_medicament(medicamentId);
        reteta.setDoza(doza);
        if (data_start != null && !data_start.isBlank()) {
            reteta.setData_start(LocalDate.parse(data_start));
        }
        retetaRepository.save(reteta);
        return "redirect:/retete";
    }

    @PostMapping("/retete/delete/{pacientId}/{medicamentId}")
    public String deleteReteta(@PathVariable Integer pacientId, @PathVariable Integer medicamentId) {
        RetetaId id = new RetetaId(pacientId, medicamentId);
        retetaRepository.deleteById(id);
        return "redirect:/retete";
    }
}
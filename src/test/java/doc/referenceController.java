/*package doc;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.humanbooster.fx.picom.business.TrancheHoraire;
import fr.humanbooster.fx.picom.exception.TrancheHoraireDejaPresenteException;
import fr.humanbooster.fx.picom.exception.TrancheHoraireInvalideException;
import fr.humanbooster.fx.picom.service.TrancheHoraireService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class TrancheHoraireRestController {

    private TrancheHoraireService trancheHoraireService;

    @GetMapping("tranchesHoraires")
    public List<TrancheHoraire> tranchesHorairesGet() {
        return trancheHoraireService.recupererTranchesHoraires();
    }

    @GetMapping("tranchesHoraires/{id}")
    public TrancheHoraire tranchesHorairesGet(@PathVariable Long id) {
        return trancheHoraireService.recupererTrancheHoraire(id);
    }

    @PostMapping("tranchesHoraires/{debutString}")
    @ResponseStatus(code=HttpStatus.CREATED)
    public TrancheHoraire tranchesHorairesPost(@PathVariable String debutString) {
        // autounboxing (Integer -> int)
        int debut = 0;
        try {
            debut = Integer.parseInt(debutString);
            return trancheHoraireService.enregistrerTrancheHoraire(new TrancheHoraire(debut));
        }
        catch (NumberFormatException e) {
            throw new TrancheHoraireInvalideException();
        }
    }

    @ExceptionHandler(TrancheHoraireInvalideException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String tranchesHorairesInvalidesException() {
        return "Données sur la tranche horaire invalide";
    }

    @ExceptionHandler(TrancheHoraireDejaPresenteException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String trancheHoraireDejaPresenteException(TrancheHoraireDejaPresenteException e) {
        return e.getMessage();
    }

    @PatchMapping("tranchesHoraires/{id}/{debutString}")
    public TrancheHoraire tranchesHorairesPatch(@PathVariable Long id, @PathVariable String debutString) {
        int debut = 0;
        try {
            debut = Integer.parseInt(debutString);
            return trancheHoraireService.mettreAJourTrancheHoraire(id, debut);
        } catch (NumberFormatException e) {
            throw new TrancheHoraireInvalideException();
        }
    }

    @DeleteMapping("tranchesHoraires/{debutString}")
    public boolean tranchesHorairesDelete(@PathVariable String debutString) {
        // autounboxing (Integer -> int)
        int debut = 0;
        try {
            debut = Integer.parseInt(debutString);
            return trancheHoraireService.supprimerTrancheHoraire(debut);
        } catch (NumberFormatException e) {
            throw new TrancheHoraireInvalideException();
        }
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public List<String> traiterDonneesInvalidesAvecDetails(ConstraintViolationException exception) {
        return exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }
}*/
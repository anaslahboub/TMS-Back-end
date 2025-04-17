package com.izorai.pfa.module2.services.report;

import com.izorai.pfa.module1.entities.camion.*;
import com.izorai.pfa.module1.services.camion.assurance.AssuranceService;
import com.izorai.pfa.module1.services.camion.cartegrise.CarteGriseService;
import com.izorai.pfa.module1.services.camion.entretien.EntrtienService;
import com.izorai.pfa.module1.services.camion.visiteTechnique.VisiteTechniqueService;
import com.izorai.pfa.module1.services.partenaire.chaufeur.ChaufeurService;
import com.izorai.pfa.module2.DTO.report.CamionInfo;
import com.izorai.pfa.module2.DTO.report.DocumentAlert;
import com.izorai.pfa.module2.DTO.report.ReportDto;
import com.izorai.pfa.module2.DTO.report.VoyageInfo;
import com.izorai.pfa.module2.entities.Voyage;
import com.izorai.pfa.module2.services.Voyage.VoyageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final VoyageService voyageService;
    private final AssuranceService assuranceService;
    private final CarteGriseService carteGriseService;
    private final VisiteTechniqueService visiteTechniqueService;
    private final EntrtienService entrtienService;
    private final ChaufeurService chaufeurService;
    private final PdfReportService pdfReportService; // Add this


    public ReportDto generateReportIN30Days(){
        ReportDto report = new ReportDto();
        LocalDate today = LocalDate.now();
        LocalDate in30Days = today.minusDays(30);

        report.setStartDate(today);
        //AJOUTER LES INFORMATION D UN VOYAGE
        List<Voyage> voyages = voyageService.getVoaygeInLast30Days();
        for (Voyage voyage : voyages) {
            VoyageInfo voyageInfo = new VoyageInfo();
            voyageInfo.setId(voyage.getId());
            voyageInfo.setRoute(voyage.getLieuDepart() + "|----> " + voyage.getLieuArrive());
            voyageInfo.setDepartureDate(voyage.getDateDepart());
            voyageInfo.setChaufeurFullName(voyage.getChaufeur().getNom()+" "+voyage.getChaufeur().getPrenom());
            voyageInfo.setCamion(voyage.getCamion().getImmatriculation() +" : "+ voyage.getCamion().getTypeCamion().getType() + " : " + voyage.getCamion().getStatus());
            report.getVoyagesPlanifies().add(voyageInfo);
        }
        List<Camion> camions=new ArrayList<>();
        for (Voyage voyage : voyages) {
            camions.add(voyage.getCamion());
        }
        for (Camion camion : camions) {
            CamionInfo camionInfo = new CamionInfo();
            camionInfo.setImmatriculation( camion.getImmatriculation());
            camionInfo.setType(camion.getTypeCamion().getType());
            camionInfo.setStatus(camion.getStatus().toString());
            report.getCamionsActive().add(camionInfo);
        }

        List<Assurance> assurances = assuranceService.getAssurancesExpirantBefore30Jours();
        for (Assurance assurance : assurances) {
            DocumentAlert documentAlert = new DocumentAlert();
            documentAlert.setDocumentType("ASSURANCE");
            documentAlert.setExpiryDate(assurance.getDateExpiration());
            documentAlert.setDaysUntilExpiry((int) ChronoUnit.DAYS.between(LocalDate.now(), assurance.getDateExpiration()));
            report.getDocumentsExpirants().add(documentAlert);
        }

        List<CarteGrise> carteGrises = carteGriseService.catreGriseExpireBefore30Days();

        for (CarteGrise carteGrise : carteGrises) {
            DocumentAlert documentAlert = new DocumentAlert();
            documentAlert.setDocumentType("CARTEGRISE");
            documentAlert.setExpiryDate(carteGrise.getDateMiseEnCirculation());
            documentAlert.setDaysUntilExpiry((int) ChronoUnit.DAYS.between(LocalDate.now(), carteGrise.getDateMiseEnCirculation()));
            report.getDocumentsExpirants().add(documentAlert);
        }

        List<VisiteTechnique> visiteTechniques = visiteTechniqueService.getVisitesTechniquesExpirantBefore30Jours();
        for (VisiteTechnique visiteTechnique : visiteTechniques) {
            DocumentAlert documentAlert = new DocumentAlert();
            documentAlert.setDocumentType("VISITETECHNIQUE");
            documentAlert.setExpiryDate(visiteTechnique.getDateExpiration());
            documentAlert.setDaysUntilExpiry((int) ChronoUnit.DAYS.between(LocalDate.now(), visiteTechnique.getDateExpiration()));
            report.getDocumentsExpirants().add(documentAlert);
        }


        List<Entretien> entretiens = entrtienService.getProchainsEntertiensBefore30Days();
        for (Entretien entretien : entretiens) {
            DocumentAlert documentAlert = new DocumentAlert();
            documentAlert.setDocumentType("ENTRETIEN");
            documentAlert.setExpiryDate(entretien.getDateProchainEntretien());
            documentAlert.setDaysUntilExpiry((int) ChronoUnit.DAYS.between(LocalDate.now(), entretien.getDateProchainEntretien()));
            report.getDocumentsExpirants().add(documentAlert);
        }

        List<Chaufeur> chaufeurs = chaufeurService.getChauffeurPermisExpiranteBefore30Days();
        for (Chaufeur chaufeur : chaufeurs) {
            DocumentAlert documentAlert = new DocumentAlert();
            documentAlert.setDocumentType("CHAUFEUR");
            documentAlert.setExpiryDate(chaufeur.getDateExpirationPermis());
            documentAlert.setDaysUntilExpiry((int) ChronoUnit.DAYS.between(LocalDate.now(), chaufeur.getDateExpirationPermis()));
            report.getDocumentsExpirants().add(documentAlert);
        }
        report.setTotalUpcomingVoyages(report.getVoyagesPlanifies().size());


        String fileName = "report_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
        pdfReportService.generatePdfReport(report, fileName);











        return report;
    }

}

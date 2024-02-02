package ch.hearc.votingservice.service.models.actions;

import ch.hearc.votingservice.service.models.Demande;

public class CreateDemandeAutorisationResult {

    private Demande demande;

    private Boolean isSuccess;

    private String message;

    public static CreateDemandeAutorisationResult actionOk(Demande demande) {

        CreateDemandeAutorisationResult result = new CreateDemandeAutorisationResult();
        result.isSuccess = Boolean.TRUE;
        result.demande = demande;
        return result;
    }

    public static CreateDemandeAutorisationResult actionKo(Demande demande, String message) {
        CreateDemandeAutorisationResult result = new CreateDemandeAutorisationResult();
        result.isSuccess = Boolean.FALSE;
        result.demande = demande;
        result.message = message;
        return result;
    }

    public Demande getDemande() {
        return demande;
    }

    public Boolean isActionOk(){
        return this.isSuccess;
    }


    public String getMessage() {
        return message;
    }
}

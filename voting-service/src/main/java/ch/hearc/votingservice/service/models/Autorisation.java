package ch.hearc.votingservice.service.models;



import java.util.Objects;
import java.util.UUID;

public class Autorisation {

    private String identifiant;

    private String autorisationCode;

    private String demandeId;

    private String campagneId;

    private Autorisation(String identifiant, String autorisationCode, String demandeId, String campagneId) {
        this.identifiant = identifiant;
        this.autorisationCode = autorisationCode;
        this.demandeId = demandeId;
        this.campagneId = campagneId;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getAutorisationCode() {
        return autorisationCode;
    }

    public String getDemandeId() {
        return demandeId;
    }

    public String getCampagneId() {
        return campagneId;
    }

    public static Autorisation nouvelleAutorisation(String autorisationCode, String demandeId, String campagneId){

        Objects.requireNonNull(autorisationCode);
        Objects.requireNonNull(demandeId);
        Objects.requireNonNull(campagneId);
        return new Autorisation(UUID.randomUUID().toString(),autorisationCode,demandeId,campagneId);

    }



}

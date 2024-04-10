package ch.hearc.adminservice.api.jms.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VoteBroadCastReceivedMessage {

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String campagneIdentifiant;
    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String objetIdentifiant;

    public VoteBroadCastReceivedMessage(String campagneIdentifiant, String objetIdentifiant) {
        this.campagneIdentifiant = campagneIdentifiant;
        this.objetIdentifiant = objetIdentifiant;
    }

    public String getCampagneIdentifiant() {
        return campagneIdentifiant;
    }

    public String getObjetIdentifiant() {
        return objetIdentifiant;
    }
}

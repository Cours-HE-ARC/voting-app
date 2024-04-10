package ch.hearc.adminservice.service.models;

import ch.hearc.adminservice.jms.models.VoteBroadCastMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VoteBroadCast {

    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String campagneIdentifiant;
    @NotEmpty
    @NotNull
    @Size(min = 3)
    private String objetIdentifiant;

    public VoteBroadCast(String campagneIdentifiant, String objetIdentifiant) {
        this.campagneIdentifiant = campagneIdentifiant;
        this.objetIdentifiant = objetIdentifiant;
    }

    public VoteBroadCastMessage toVoteBroadCastMessage(){
        return new VoteBroadCastMessage(this.campagneIdentifiant,this.objetIdentifiant);
    }
    public String getCampagneIdentifiant() {
        return campagneIdentifiant;
    }

    public String getObjetIdentifiant() {
        return objetIdentifiant;
    }


}

package ch.hearc.adminservice.service.models;

import ch.hearc.adminservice.repository.entity.DemandeEntity;

import java.util.UUID;

public class Demande {

    private String nom;

    private String identifiant;

    private String prenom;

    private String campagneId;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(String campagneId) {
        this.campagneId = campagneId;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public Demande(String nom, String prenom, String campagneId) {
        this.nom = nom;
        this.prenom = prenom;
        this.campagneId = campagneId;
        this.identifiant = UUID.randomUUID().toString();
    }

    public Demande () {}

    public static DemandeEntity toDemandeEntity(Demande demande){
        DemandeEntity demandeEntity = new DemandeEntity();
        demandeEntity.setCampagneId(demande.campagneId);
        demandeEntity.setIdentifiant(demande.identifiant);
        demandeEntity.setNom(demande.nom);
        demandeEntity.setPrenom(demande.prenom);
        return demandeEntity;
    }

    public Demande(DemandeEntity demandeEntity){
        this.nom = demandeEntity.getNom();
        this.prenom = demandeEntity.getPrenom();
        this.identifiant = demandeEntity.getIdentifiant();
        this.campagneId = demandeEntity.getCampagneId();
    }
}

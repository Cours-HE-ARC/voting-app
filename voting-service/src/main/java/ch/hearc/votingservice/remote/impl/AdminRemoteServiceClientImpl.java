package ch.hearc.votingservice.remote.impl;

import ch.hearc.votingservice.remote.AdminRemoteServiceClient;
import ch.hearc.votingservice.remote.models.CampagneResponseBody;
import ch.hearc.votingservice.remote.models.ListCampagnesResponseBody;
import ch.hearc.votingservice.shared.CampagneStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class AdminRemoteServiceClientImpl implements AdminRemoteServiceClient {


    @Autowired
    AdminRestClient adminRestClient;


    @Override
    public ListCampagnesResponseBody getCampagnesOuvertes() {

        ResponseEntity<ListCampagnesResponseBody> campagnesResponseBody = adminRestClient.getcampagnesOuvertes();

        return campagnesResponseBody.getBody();
    }

    @Override
    public Boolean isCampagneExistAndOuverte(String campagneIdentifiant) throws Error400Exception{



        ResponseEntity<Optional<CampagneResponseBody>> responseEntity = adminRestClient.getCampagneByIdentifiant(campagneIdentifiant);

        if(responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)){
            throw new Error400Exception("Entity not Found");
        }else{
            return responseEntity.getBody().get().getStatus().equals(CampagneStatus.OPENED);
        }

    }

    @Override
    public Optional<CampagneResponseBody> getCampagneByIdentifiant(String identifiant){

        ResponseEntity<Optional<CampagneResponseBody>> responseEntity = adminRestClient.getCampagneByIdentifiant(identifiant);

        return responseEntity.getBody();

    }
}

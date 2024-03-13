package ch.hearc.votingservice.remote.impl;

import ch.hearc.votingservice.remote.models.CampagneResponseBody;
import ch.hearc.votingservice.remote.models.ListCampagnesResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient(name = "admin-service",configuration = FeignConfig.class)
public interface AdminRestClient {

    @RequestMapping("/campagne?status=OPENED")
    ResponseEntity<ListCampagnesResponseBody> getcampagnesOuvertes();

    @RequestMapping("/campagne/{campagneIdid}")
    ResponseEntity<Optional<CampagneResponseBody>> getCampagneByIdentifiant(@PathVariable("campagneId") String postId);
}

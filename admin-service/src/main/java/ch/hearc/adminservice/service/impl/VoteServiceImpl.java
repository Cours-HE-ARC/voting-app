package ch.hearc.adminservice.service.impl;

import ch.hearc.adminservice.repository.AutorisationRepository;
import ch.hearc.adminservice.repository.CampagneRespository;
import ch.hearc.adminservice.repository.InvalidAttemptVotingRepository;
import ch.hearc.adminservice.repository.VoteRepository;
import ch.hearc.adminservice.repository.entity.*;
import ch.hearc.adminservice.service.VoteService;
import ch.hearc.adminservice.service.models.Vote;
import ch.hearc.adminservice.service.models.actions.VoteSubmitedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    AutorisationRepository autorisationRepository;
    @Autowired
    CampagneRespository campagneRespository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    InvalidAttemptVotingRepository invalidAttemptVotingRepository;

    @Override
    public VoteSubmitedResult validateVote(Vote vote) {

        Optional<CampagneEntity> optionalCampagneEntity = campagneRespository.findByIdentifiant(vote.getCampagneIdentifiant());

        if(optionalCampagneEntity.isPresent()){

            CampagneEntity campagneEntity = optionalCampagneEntity.get();

            Optional<ObjetEntity> optionalObjetEntity = campagneEntity.getObjets().stream().filter(entity -> {
                return entity.getIdentifiant().equals(vote.getObjetIdentifiant());
            }).findFirst();

            if(optionalObjetEntity.isPresent()){
                ObjetEntity objetEntity = optionalObjetEntity.get();
                Optional<AutorisationEntity> optionalAutorisationEntity = autorisationRepository.findByAutorisationCode(vote.getAutorisationCode());

                if(optionalAutorisationEntity.isPresent() && !optionalAutorisationEntity.get().getUsed()){
                    AutorisationEntity autorisationEntity = optionalAutorisationEntity.get();

                    VoteEntity voteEntity = VoteEntity.newInstanceFrom(objetEntity,autorisationEntity);
                    voteRepository.save(voteEntity);
                    autorisationEntity.setUsed(Boolean.TRUE);
                    autorisationRepository.save(autorisationEntity);

                    return VoteSubmitedResult.ok("Vote successfully validated");
                }else{

                    InvalidVotingAttemptEntity invalidVotingAttemptEntity = InvalidVotingAttemptEntity
                            .newInstance(vote.getCampagneIdentifiant(),vote.getObjetIdentifiant(),vote.getAutorisationCode(),"Autorisation code not longer exist or already used");
                    invalidAttemptVotingRepository.save(invalidVotingAttemptEntity);

                    return VoteSubmitedResult.ko(
                            String.format("Autorisation code [%s] not longer exist or already used",
                                    vote.getAutorisationCode()));
                }

            }else{

                InvalidVotingAttemptEntity invalidVotingAttemptEntity = InvalidVotingAttemptEntity
                        .newInstance(vote.getCampagneIdentifiant(),vote.getObjetIdentifiant(),vote.getAutorisationCode(),"Objet with identifiant provided doesn't exist in the campagne");
                invalidAttemptVotingRepository.save(invalidVotingAttemptEntity);

                return VoteSubmitedResult.ko(
                        String.format("Objet with identifiant provided [%s] doesn't exist in the campagne: %s",
                                vote.getObjetIdentifiant(),
                                vote.getCampagneIdentifiant()));
            }


        }else{

            InvalidVotingAttemptEntity invalidVotingAttemptEntity = InvalidVotingAttemptEntity
                    .newInstance(vote.getCampagneIdentifiant(),vote.getObjetIdentifiant(),vote.getAutorisationCode(),"Campagne with identifiant provided doesn't exist");
            invalidAttemptVotingRepository.save(invalidVotingAttemptEntity);

            return VoteSubmitedResult.ko(
                    String.format("Campagne with identifiant provided [%s] doesn't exist",vote.getCampagneIdentifiant()));
        }




    }
}

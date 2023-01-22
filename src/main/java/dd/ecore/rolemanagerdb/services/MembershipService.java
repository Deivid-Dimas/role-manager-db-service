package dd.ecore.rolemanagerdb.services;

import dd.ecore.rolemanagerdb.entity.Membership;
import dd.ecore.rolemanagerdb.entity.Role;
import dd.ecore.rolemanagerdb.repository.MembershipRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepo membershipRepo;


    public ResponseEntity<List<Membership>> getAll(){
        return new ResponseEntity<>(membershipRepo.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<Membership> getById(String id){
        Optional<Membership> response = membershipRepo.findById(id);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Membership>> getByUserId(String id){
        List<Membership> response = membershipRepo.findByUserId(id);
        if(!response.isEmpty()){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Membership> saveMembership(Membership body){
        ResponseEntity response;
        if(!body.getUserId().isEmpty() && !body.getTeamId().isEmpty()){
            response = new ResponseEntity<>(membershipRepo.save(body),HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    public ResponseEntity<Membership>  updateMembership(Membership body) {
        Optional<Membership> optMembership = membershipRepo.findById(body.getId());
        ResponseEntity<Membership> response;
        if(optMembership.isPresent()){
            response = new ResponseEntity<>(membershipRepo.save(body),HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return response;
    }
}

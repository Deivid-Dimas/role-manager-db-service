package dd.ecore.rolemanagerdb.controller;

import dd.ecore.rolemanagerdb.entity.Membership;
import dd.ecore.rolemanagerdb.services.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Membership>> getAll(){
        return membershipService.getAll();
    }

    @PostMapping("/get-userid")
    public ResponseEntity<List<Membership>> getByUserId(@RequestBody Membership body){
        return membershipService.getByUserId(body.getUserId());
    }

    @PostMapping("/by-id")
    public ResponseEntity<Membership> getById(@RequestBody Membership body){
        return membershipService.getById(body.getId());
    }

    @PostMapping("/new-membership")
    public ResponseEntity<Membership> newMembership(@RequestBody Membership body){
        return membershipService.saveMembership(body);
    }

    @PutMapping("/update-membership")
    public ResponseEntity<Membership> updateMembership(@RequestBody Membership body){
        return membershipService.updateMembership(body);
    }
}

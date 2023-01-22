package dd.ecore.rolemanagerdb.controller;

import dd.ecore.rolemanagerdb.entity.Membership;
import dd.ecore.rolemanagerdb.entity.RoleAssociation;
import dd.ecore.rolemanagerdb.services.MembershipService;
import dd.ecore.rolemanagerdb.services.RoleAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role-association")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class RoleAssociationController {

    @Autowired
    private RoleAssociationService roleAssociationService;

    @GetMapping("/get-all")
    public ResponseEntity<List<RoleAssociation>> getAll(){
        return roleAssociationService.getAll();
    }

    @PostMapping("/by-id")
    public ResponseEntity<RoleAssociation> getById(@RequestBody RoleAssociation body){
        return roleAssociationService.getById(body.getId());
    }
    @PostMapping("/by-userid")
    public ResponseEntity<RoleAssociation>  getByUserId(@RequestBody RoleAssociation body){
        return roleAssociationService.getByUserId(body.getUserId());
    }

    @PostMapping("/new-role-association")
    public ResponseEntity<RoleAssociation> newMembership(@RequestBody RoleAssociation body){
        return roleAssociationService.saveAssociation(body);
    }

    @PutMapping("update-role-association")
    public ResponseEntity<RoleAssociation> updateMembership(@RequestBody RoleAssociation body){
        return roleAssociationService.updateAssociation(body);
    }
}

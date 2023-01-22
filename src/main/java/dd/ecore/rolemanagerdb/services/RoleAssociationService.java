package dd.ecore.rolemanagerdb.services;

import dd.ecore.rolemanagerdb.entity.Membership;
import dd.ecore.rolemanagerdb.entity.Role;
import dd.ecore.rolemanagerdb.entity.RoleAssociation;
import dd.ecore.rolemanagerdb.repository.RoleAssociationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleAssociationService {

    @Autowired
    private RoleAssociationRepo roleAssociationRepo;
    @Autowired
    private RolesService rolesService;

    public ResponseEntity<List<RoleAssociation>> getAll(){
        return new ResponseEntity<>(roleAssociationRepo.findAll(), HttpStatus.OK);
    }
    public ResponseEntity<RoleAssociation> getByUserId(String id){
        return new ResponseEntity<>(roleAssociationRepo.findByUserId(id), HttpStatus.OK);
    }

    public ResponseEntity<RoleAssociation> getById(String id){
        Optional<RoleAssociation> response = roleAssociationRepo.findById(id);
        if(response.isPresent()){
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<RoleAssociation> saveAssociation(RoleAssociation body){
        ResponseEntity response;
        RoleAssociation roleAssociation = roleAssociationRepo.findByUserId(body.getUserId());
        if (roleAssociation != null){
            body.setId(roleAssociation.getId());
            response = updateAssociation(body);
        }else {
            if (!body.getUserId().isEmpty()) {

                Role role = rolesService.getByName("Developer").getBody();
                if (role.getId().isEmpty()) {
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }

                List<String> roles = new ArrayList<>();
                if (!body.getRoleId().contains(role.getId())) {
                    roles.addAll(body.getRoleId());
                    roles.add(role.getId());
                    body.setRoleId(roles);

                }
                response = new ResponseEntity<>(roleAssociationRepo.save(body), HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        return response;
    }
    public ResponseEntity<RoleAssociation>  updateAssociation(RoleAssociation body) {
        Optional<RoleAssociation> optMembership = roleAssociationRepo.findById(body.getId());
        ResponseEntity<RoleAssociation> response;
        if(optMembership.isPresent()){
            response = new ResponseEntity<>(roleAssociationRepo.save(body),HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return response;
    }
}

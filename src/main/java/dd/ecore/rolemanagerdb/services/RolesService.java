package dd.ecore.rolemanagerdb.services;

import dd.ecore.rolemanagerdb.entity.Role;
import dd.ecore.rolemanagerdb.repository.RolesRepo;
import org.apache.catalina.mbeans.RoleMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {


    @Autowired
    private RolesRepo rolesRepo;

    public ResponseEntity<List<Role>> getRoles(){
        List<Role> response = rolesRepo.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public ResponseEntity<Role> getByName(String name){
        Role response = rolesRepo.findByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Role> saveRole(Role body){
        ResponseEntity response;
        if(!body.getName().isEmpty() &&
                !body.getDescription().isEmpty()){
            response = new ResponseEntity<>(rolesRepo.save(body),HttpStatus.OK);
        }else {
            response = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    public ResponseEntity<Role> updateRole(Role body) {
        Optional<Role> optRole = rolesRepo.findById(body.getId());
        ResponseEntity<Role> response;
        if(optRole.isPresent()){
            response = new ResponseEntity<>(rolesRepo.save(body),HttpStatus.OK);
        }else{
            response = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return response;
    }

}

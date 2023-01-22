package dd.ecore.rolemanagerdb.controller;

import dd.ecore.rolemanagerdb.entity.Role;
import dd.ecore.rolemanagerdb.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Role>> getRoles(){
        return rolesService.getRoles();
    }
    @GetMapping("/by-name")
    public ResponseEntity<Role> getRolesByName(@RequestParam String name){
        return rolesService.getByName(name);
    }
    @PutMapping("/update-role")
    public ResponseEntity<Role> updateRoles(@RequestBody Role body){
        return rolesService.updateRole(body);
    }

    @PostMapping("/new-role")
    public ResponseEntity<Role> getRoles(@RequestBody Role body){
        return rolesService.saveRole(body);
    }


}

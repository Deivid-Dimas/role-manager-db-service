package dd.ecore.rolemanagerdb.controller;

import dd.ecore.rolemanagerdb.entity.Resources;
import dd.ecore.rolemanagerdb.repository.ResourcesRepo;
import dd.ecore.rolemanagerdb.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/resources")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class ResourcesController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("get-all")
    public ResponseEntity<List<Resources>> getAll(){
        return resourceService.getResources();
    }

}


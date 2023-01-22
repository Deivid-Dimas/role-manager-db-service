package dd.ecore.rolemanagerdb.services;

import dd.ecore.rolemanagerdb.entity.Resources;
import dd.ecore.rolemanagerdb.repository.ResourcesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourcesRepo resourcesRepo;

    public ResponseEntity<List<Resources>> getResources(){
        List<Resources> response = resourcesRepo.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

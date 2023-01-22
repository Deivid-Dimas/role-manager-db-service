package dd.ecore.rolemanagerdb.repository;

import dd.ecore.rolemanagerdb.entity.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepo extends MongoRepository<Resources,String> {

}

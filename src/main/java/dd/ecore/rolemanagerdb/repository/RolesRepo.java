package dd.ecore.rolemanagerdb.repository;

import dd.ecore.rolemanagerdb.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends MongoRepository<Role,String> {

    public Role findByName(String name);
}

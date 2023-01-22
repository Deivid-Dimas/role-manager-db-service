package dd.ecore.rolemanagerdb.repository;

import dd.ecore.rolemanagerdb.entity.RoleAssociation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAssociationRepo extends MongoRepository<RoleAssociation,String> {
    public RoleAssociation findByUserId(String id);
}

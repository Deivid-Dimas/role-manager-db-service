package dd.ecore.rolemanagerdb.repository;

import dd.ecore.rolemanagerdb.entity.Membership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepo extends MongoRepository<Membership, String> {

    public List<Membership> findByUserId(String id);
}

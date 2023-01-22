package dd.ecore.rolemanagerdb;

import dd.ecore.rolemanagerdb.entity.Membership;
import dd.ecore.rolemanagerdb.entity.Resources;
import dd.ecore.rolemanagerdb.entity.Role;
import dd.ecore.rolemanagerdb.entity.RoleAssociation;
import dd.ecore.rolemanagerdb.repository.ResourcesRepo;
import dd.ecore.rolemanagerdb.services.ResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RoleManagerDbApplicationTests {

	@Autowired
	ResourceService resourceService;

	@Test
	void contextLoads() {
	}

	@Test
	void testDataBaseConnections() {
		List<Resources> resourcesList = resourceService.getResources().getBody();
		assert !resourcesList.isEmpty();
	}

	@Test
	void testResourcesController_getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Resources[]> obj = restTemplate.getForEntity("http://localhost:8090/resources/get-all", Resources[].class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody()[0] instanceof Resources);

	}

	@Test
	void testRolesController_getRoles() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Role[]> obj = restTemplate.getForEntity("http://localhost:8090/roles/get-all", Role[].class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody()[0] instanceof Role);

	}

	@Test
	void testRolesController_getRolesByName() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Role> obj = restTemplate.getForEntity("http://localhost:8090/roles/by-name?name=Developer", Role.class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody() instanceof Role);

	}

	@Test
	void testRolesController_updateRole() {
		RestTemplate restTemplate = new RestTemplate();
		Role role = new Role();
		role.setId("63cb0bd880db2e670031703f");
		role.setName("Develope");
		role.setDescription("This role allow the user to interact with the platform, add text to the histories and make minimal changes to the user stories");

		restTemplate.put("http://localhost:8090/roles/update-role", role);
		ResponseEntity<Role> obj = restTemplate.getForEntity("http://localhost:8090/roles/by-name?name=Develope", Role.class);


		assert (obj.getStatusCode().value() == 200
				&& obj.getBody() instanceof Role
				&& obj.getBody().getName().equals(role.getName()));

		role.setName("Developer");
		restTemplate.put("http://localhost:8090/roles/update-role", role);
	}

	//	@Test
	void testRolesController_newRole() {
		RestTemplate restTemplate = new RestTemplate();
		Role role = new Role();
		role.setId("63cb0bd880db2e670031703f");
		role.setName("Developer");
		role.setDescription("This role allow the user to interact with the platform, add text to the histories and make minimal changes to the user stories");

		ResponseEntity<Role> obj = restTemplate.postForEntity("http://localhost:8090/roles/new-role", role, Role.class);


		assert (obj.getStatusCode().value() == 200
				&& obj.getBody() instanceof Role
				&& obj.getBody().getName().equals(role.getName()));
	}

	@Test
	void testRolesAssociationController_getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RoleAssociation[]> obj = restTemplate.getForEntity("http://localhost:8090/role-association/get-all", RoleAssociation[].class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody()[0] instanceof RoleAssociation);

	}

	@Test
	void testRolesAssociationController_getRolesAssociationById() {
		RestTemplate restTemplate = new RestTemplate();
		RoleAssociation roleAssociation = new RoleAssociation();
		roleAssociation.setId("63cc98b0ab3ed40ee67adb40");
		ResponseEntity<RoleAssociation> obj = restTemplate.postForEntity("http://localhost:8090/role-association/by-id", roleAssociation, RoleAssociation.class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody() instanceof RoleAssociation && obj.getBody().getId().equals(roleAssociation.getId()));

	}

	@Test
	void testRolesAssociationController_getRolesAssociationByUserId() {
		RestTemplate restTemplate = new RestTemplate();
		RoleAssociation roleAssociation = new RoleAssociation();
		roleAssociation.setUserId("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c");
		ResponseEntity<RoleAssociation> obj = restTemplate.postForEntity("http://localhost:8090/role-association/by-userid", roleAssociation, RoleAssociation.class);
		assert (obj.getStatusCode().value() == 200
				&& obj.getBody() instanceof RoleAssociation
				&& obj.getBody().getUserId().equals(roleAssociation.getUserId()));

	}

	@Test
	void testRolesAssociationController_updateRoleAssociation() {
		RestTemplate restTemplate = new RestTemplate();
		RoleAssociation role = new RoleAssociation();
		role.setId("63cc98b0ab3ed40ee67adb40");
		role.setUserId("fd282131-d8aa-4819-b0c8-d9e0bfb1b75c");
		List<String> list = new ArrayList<>();
		list.add("63cb0bd880db2e670031703f");
		role.setRoleId(list);

		ResponseEntity<RoleAssociation> obj = restTemplate.postForEntity("http://localhost:8090/role-association/by-id", role, RoleAssociation.class);

		if (obj.getBody().isActive()) {
			role.setActive(false);
		} else {
			role.setActive(true);
		}

		restTemplate.put("http://localhost:8090/role-association/update-role-association", role);
		obj = restTemplate.postForEntity("http://localhost:8090/role-association/by-id", role, RoleAssociation.class);


		assert (obj.getStatusCode().value() == 200 && (obj.getBody().isActive() && role.isActive()));
		if (obj.getBody().isActive()) {
			role.setActive(false);
		} else {
			role.setActive(true);
		}
		restTemplate.put("http://localhost:8090/role-association/update-role-association", role);
	}

	@Test
	void testRolesAssociationController_newRoleAssociation() {
		RestTemplate restTemplate = new RestTemplate();
		RoleAssociation role = new RoleAssociation();
		//role.setId("63cb0bd880db2e670031703f");
		role.setUserId("fd282131-d8aa-4819-b0c8-d9e0bfb1b75");
		List<String> list = new ArrayList<>();
		list.add("63cd441dfeba663814ffbf6d");
		role.setRoleId(list);
		role.setActive(true);
		ResponseEntity<RoleAssociation> obj = restTemplate.postForEntity("http://localhost:8090/role-association/new-role-association", role, RoleAssociation.class);

		assert (obj.getStatusCode().value() == 200);
	}

	@Test
	void testMembershipController_getAll() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Membership[]> obj = restTemplate.getForEntity("http://localhost:8090/membership/get-all", Membership[].class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody()[0] instanceof Membership);

	}

	@Test
	void testMembershipController_getById() {
		RestTemplate restTemplate = new RestTemplate();
		Membership membership = new Membership();
		membership.setId("63cc0920e844df5c62a0e092");
		ResponseEntity<Membership> obj = restTemplate.postForEntity("http://localhost:8090/membership/by-id", membership, Membership.class);
		assert (obj.getStatusCode().value() == 200 && obj.getBody() instanceof Membership && obj.getBody().getId().equals(membership.getId()));

	}

	@Test
	void testMembershipController_getByUserId() {
		RestTemplate restTemplate = new RestTemplate();
		Membership membership = new Membership();
		membership.setUserId("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e");
		ResponseEntity<Membership[]> obj = restTemplate.postForEntity("http://localhost:8090/membership/get-userid", membership, Membership[].class);
		assert (obj.getStatusCode().value() == 200
				&& obj.getBody()[0] instanceof Membership
				&& obj.getBody()[0].getUserId().equals(membership.getUserId()));

	}

	//@Test
	void testMembershipController_new() {
		RestTemplate restTemplate = new RestTemplate();
		Membership membership = new Membership();
		membership.setId("63cc0920e844df5c62a0e092");
		membership.setUserId("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e");
		membership.setTeamId("7676a4bf-adfe-415c-941b-1739af07039b");
		membership.setActive(true);
		ResponseEntity<Membership> obj = restTemplate.postForEntity("http://localhost:8090/membership/new-membership", membership, Membership.class);

		assert (obj.getStatusCode().value() == 200);
	}

	@Test
	void testMembershipController_update() {
		RestTemplate restTemplate = new RestTemplate();
		Membership membership = new Membership();
		membership.setId("63cc0920e844df5c62a0e092");
		membership.setUserId("371d2ee8-cdf4-48cf-9ddb-04798b79ad9e");
		membership.setTeamId("7676a4bf-adfe-415c-941b-1739af07039b");

		ResponseEntity<Membership> obj = restTemplate.postForEntity("http://localhost:8090/membership/by-id", membership, Membership.class);

		if (obj.getBody().isActive()) {
			membership.setActive(false);
		} else {
			membership.setActive(true);
		}

		restTemplate.put("http://localhost:8090/membership/update-membership", membership);
		obj = restTemplate.postForEntity("http://localhost:8090/membership/by-id", membership, Membership.class);


		assert (obj.getStatusCode().value() == 200 && (obj.getBody().isActive() && membership.isActive()));
		if (obj.getBody().isActive()) {
			membership.setActive(false);
		} else {
			membership.setActive(true);
		}
		restTemplate.put("http://localhost:8090/membership/update-membership", membership);

	}
}


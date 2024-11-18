package at.pmrc.mongo.persistence.repositories;

import at.pmrc.mongo.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("mongoUserRepository")
public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findBy_id(ObjectId _id);

    List<User> findByFirstnameAndLastname(String firstname, String lastname);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByGender(String gender);

    List<User> findByYear (String year);
}

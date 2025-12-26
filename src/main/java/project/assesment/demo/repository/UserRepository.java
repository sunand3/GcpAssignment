package project.assesment.demo.repository;

import com.google.cloud.datastore.*;
import project.assesment.demo.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String KIND = "User";
    private final Datastore datastore;

    public UserRepository() {
        this.datastore = DatastoreOptions.getDefaultInstance().getService();
    }

    public void save(User user) {
        Key key = datastore.newKeyFactory().setKind(KIND).newKey(user.getEmail());
        Entity entity = Entity.newBuilder(key)
                .set("name", user.getName())
                .set("dob", user.getDob() == null ? "" : user.getDob())
                .set("email", user.getEmail())
                .set("password", user.getPassword() == null ? "" : user.getPassword())
                .set("phone", user.getPhone() == null ? "" : user.getPhone())
                .set("gender", user.getGender() == null ? "" : user.getGender())
                .set("address", user.getAddress() == null ? "" : user.getAddress())
                .build();
        datastore.put(entity);
    }

    public User findByEmail(String email) {
        Key key = datastore.newKeyFactory().setKind(KIND).newKey(email);
        Entity entity = datastore.get(key);
        if (entity == null) return null;
        return entityToUser(entity);
    }

    public List<User> findAll() {
        Query<Entity> query = Query.newEntityQueryBuilder().setKind(KIND).build();
        QueryResults<Entity> results = datastore.run(query);
        List<User> users = new ArrayList<>();
        while (results.hasNext()) {
            Entity e = results.next();
            users.add(entityToUser(e));
        }
        return users;
    }

    public void delete(String email) {
        Key key = datastore.newKeyFactory().setKind(KIND).newKey(email);
        datastore.delete(key);
    }

    public List<User> searchByName(String search) {
        List<User> result = new ArrayList<>();
        for (User u : findAll()) {
            if (u.getName() != null && u.getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(u);
            }
        }
        return result;
    }

    private User entityToUser(Entity e) {
        User user = new User();
        user.setEmail(e.getString("email"));
        user.setName(e.getString("name"));
        user.setDob(e.contains("dob") ? e.getString("dob") : null);
        user.setPassword(e.contains("password") ? e.getString("password") : null);
        user.setPhone(e.contains("phone") ? e.getString("phone") : null);
        user.setGender(e.contains("gender") ? e.getString("gender") : null);
        user.setAddress(e.contains("address") ? e.getString("address") : null);
        return user;
    }
}

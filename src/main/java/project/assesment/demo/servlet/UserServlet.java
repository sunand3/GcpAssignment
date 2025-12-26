package project.assesment.demo.servlet;


import com.google.gson.Gson;
import project.assesment.demo.model.User;
import project.assesment.demo.repository.UserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("search");
        List<User> users;
        if (search != null && !search.isEmpty()) {
            users = userRepository.searchByName(search);
        } else {
            users = userRepository.findAll();
        }
        resp.setContentType("application/json");
        resp.getWriter().write("{\"status\":\"backend working\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = gson.fromJson(req.getReader(), User.class);

        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getName() == null || user.getName().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Name and email are required");
            return;
        }

        userRepository.save(user);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        if (email != null) {
            userRepository.delete(email);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

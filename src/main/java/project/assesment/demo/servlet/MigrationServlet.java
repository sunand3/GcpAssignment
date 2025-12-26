package project.assesment.demo.servlet;

import project.assesment.demo.repository.UserRepository;
import project.assesment.demo.service.MigrationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/migrate")
public class MigrationServlet extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();
    private final MigrationService migrationService = new MigrationService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        migrationService.migrate(userRepository.findAll());
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Migration completed successfully!");
    }
}

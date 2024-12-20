package gitling.studio.app.servlet;

import gitling.studio.app.DataLayer.Disc;
import gitling.studio.app.RepositoryLayer.DiscRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//discList
@WebServlet("/discList")
public class DiscListServlet extends HttpServlet {
    private DiscRepository discRepository;
    private static final Logger logger = Logger.getLogger(DiscListServlet.class.getName());

    @Override
    public void init() throws ServletException {
        try {
            logger.info("Initializing DiscListServlet...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/your_database", "your_username", "your_password"
            );
            discRepository = new DiscRepository(connection);
            logger.info("DiscListServlet initialized successfully.");
        } catch (SQLException e) {
            logger.severe("Error initializing DiscListServlet: " + e.getMessage());
            throw new ServletException("Error initializing servlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            List<Disc> discs = discRepository.readDiscs();

            response.getWriter().println("<!DOCTYPE html>");
            response.getWriter().println("<html>");
            response.getWriter().println("<head>");
            response.getWriter().println("<title>Список дисков</title>");
            response.getWriter().println("<style>");
            response.getWriter().println("body { font-family: 'Arial', sans-serif; font-size: 14px; }");
            response.getWriter().println("</style>");
            response.getWriter().println("</head>");
            response.getWriter().println("<body>");
            response.getWriter().println("<h1>Список дисков</h1>");

            // Форма для добавления нового диска
            response.getWriter().println("<form method='post'>");
            response.getWriter().println("<input type='hidden' name='action' value='create'>");
            response.getWriter().println("<label for='title'>Название:</label>");
            response.getWriter().println("<input type='text' id='title' name='title' required><br>");
            response.getWriter().println("<label for='mediaTypeId'>ID типа носителя:</label>");
            response.getWriter().println("<input type='number' id='mediaTypeId' name='mediaTypeId' required><br>");
            response.getWriter().println("<label for='description'>Описание:</label>");
            response.getWriter().println("<textarea id='description' name='description'></textarea><br>");
            response.getWriter().println("<button type='submit'>Добавить</button>");
            response.getWriter().println("</form>");

            // Таблица с дисками
            response.getWriter().println("<table border='1'>");
            response.getWriter().println("<tr><th>ID</th><th>Название</th><th>Описание</th><th>Тип носителя</th><th>Категория</th><th>Действия</th></tr>");
            for (Disc disc : discs) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + disc.getDiscId() + "</td>");
                response.getWriter().println("<td>" + disc.getTitle() + "</td>");
                response.getWriter().println("<td>" + disc.getDescription() + "</td>");
                response.getWriter().println("<td>" + disc.getMediaTypeName() + "</td>");
                response.getWriter().println("<td>" + disc.getCategoryName() + "</td>");
                response.getWriter().println("<td>");
                response.getWriter().println("<form method='post' style='display:inline;'>");
                response.getWriter().println("<input type='hidden' name='action' value='delete'>");
                response.getWriter().println("<input type='hidden' name='id' value='" + disc.getDiscId() + "'>");
                response.getWriter().println("<button type='submit'>Удалить</button>");
                response.getWriter().println("</form>");
                response.getWriter().println("<form method='post' style='display:inline;'>");
                response.getWriter().println("<input type='hidden' name='action' value='edit'>");
                response.getWriter().println("<input type='hidden' name='id' value='" + disc.getDiscId() + "'>");
                response.getWriter().println("<input type='text' name='title' value='" + disc.getTitle() + "' required>");
                response.getWriter().println("<input type='number' name='mediaTypeId' value='" + disc.getMediaTypeName() + "' required>");
                response.getWriter().println("<textarea name='description'>" + disc.getDescription() + "</textarea>");
                response.getWriter().println("<button type='submit'>Сохранить</button>");
                response.getWriter().println("</form>");
                response.getWriter().println("</td>");
                response.getWriter().println("</tr>");
            }
            response.getWriter().println("</table>");
            response.getWriter().println("</body>");
            response.getWriter().println("</html>");
        } catch (SQLException e) {
            logger.severe("Error fetching disc list: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching disc list.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("create".equals(action)) {
                String title = request.getParameter("title");
                long mediaTypeId = Long.parseLong(request.getParameter("mediaTypeId"));
                String description = request.getParameter("description");
                discRepository.createDisc(title, mediaTypeId, description);
            } else if ("delete".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                discRepository.deleteDisc(id);
            } else if ("edit".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                String newTitle = request.getParameter("title");
                long newMediaTypeId = Long.parseLong(request.getParameter("mediaTypeId"));
                String newDescription = request.getParameter("description");
                discRepository.updateDisc(id, newTitle, newMediaTypeId, newDescription);
            }
            response.sendRedirect(request.getContextPath() + "/discList");
        } catch (SQLException e) {
            logger.severe("Error processing request: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }
}

package phoneBook.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import phoneBook.config.ObjectMapperConfiguration;
import phoneBook.domain.PhoneBookItem;
import phoneBook.service.PhoneBookItemService;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/phoneBookItems")
public class PhoneBookItemServlet extends HttpServlet {

    private PhoneBookItemService phoneBookItemService = new PhoneBookItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatePhoneBookItemRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), CreatePhoneBookItemRequest.class);
        try {
            phoneBookItemService.createPhoneBookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            phoneBookItemService.deletePhoneBookItem(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UpdatePhoneBookItemRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), UpdatePhoneBookItemRequest.class);
        try {
            phoneBookItemService.updatePhoneBookItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<PhoneBookItem> phoneBookItems = phoneBookItemService.getPhoneBookItems();
            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phoneBookItems);
            resp.getWriter().print(response);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }
}

package phoneBook.web;

import phoneBook.config.ObjectMapperConfiguration;
import phoneBook.domain.PhoneBookItem;
import phoneBook.service.PhoneBookItemService;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/phoneBookItems")
public class PhoneBookItemServlet extends HttpServlet {

    private PhoneBookItemService phoneBookItemService = new PhoneBookItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CreatePhoneBookItemRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), CreatePhoneBookItemRequest.class);
        try {
            phoneBookItemService.createPhoneBookItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            if (req.getParameter("id").contains(id)) {
                phoneBookItemService.deletePhoneBookItem(Long.parseLong(id));
            } else {
                phoneBookItemService.deleteAllPhoneBookItems();
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
//            List<PhoneBookItem> phoneBookItems = phoneBookItemService.getPhoneBookItems();
            PhoneBookItem request = ObjectMapperConfiguration.getObjectMapper()
                    .readValue(req.getReader(), PhoneBookItem.class);
//            phoneBookItemService.getPhoneBookItems();
            phoneBookItemService.getOnePhoneBookItem(Long.parseLong(id));
//            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phoneBookItems);
//            resp.getWriter().print(response);
            resp.getWriter().print(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }
}

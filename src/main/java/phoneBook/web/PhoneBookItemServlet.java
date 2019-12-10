package phoneBook.web;

import phoneBook.config.ObjectMapperConfiguration;
import phoneBook.domain.PhoneBookItem;
import phoneBook.service.PhoneBookItemService;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.GetPhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Long.parseLong;

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
            if (new long[]{parseLong(id)}.length < Long.parseLong(id)) {
                phoneBookItemService.deletePhoneBookItem(Long.parseLong(id));
            } else {
                phoneBookItemService.deletePhoneBookItems(new long[]{Long.parseLong(id)});
            }
        } catch (SQLException |
                ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        UpdatePhoneBookItemRequest request = ObjectMapperConfiguration.getObjectMapper()
                .readValue(req.getReader(), UpdatePhoneBookItemRequest.class);
        try {
            phoneBookItemService.updatePhoneBookItem(parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        try {
            if (id != null) {
                GetPhoneBookItemRequest request = phoneBookItemService.getPhoneBookItem(parseLong(id));
                String responseForOne = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(request);
                resp.getWriter().print(responseForOne);
            } else {
                List<PhoneBookItem> phoneBookItems = phoneBookItemService.getPhoneBookItems();
                String responseForAll = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(phoneBookItems);
                resp.getWriter().print(responseForAll);
            }

        } catch (SQLException |
                ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }
}
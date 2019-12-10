package phoneBook.persistance;

import phoneBook.domain.PhoneBookItem;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.GetPhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookItemRepository {

    public void createPhoneBookItem(CreatePhoneBookItemRequest request)
            throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO phone_book_item (firstName, lastName, phoneNumber, country, city)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.setString(4, request.getCountry());
            preparedStatement.setString(5, request.getCity());
            preparedStatement.executeUpdate();
        }
    }

    public void updatePhoneBookItem(long id, UpdatePhoneBookItemRequest request)
            throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE phone_book_item SET firstName=?, lastName=?  WHERE id=?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getFirstName());
            preparedStatement.setString(2, request.getLastName());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deletePhoneBookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_book_item WHERE id=?";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deletePhoneBookItems(long[] id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM phone_book_item WHERE id in (?)";
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < id.length; i++) {
                preparedStatement.setLong(1, id[i]);
                preparedStatement.executeUpdate();
            }
        }
    }


    public List<PhoneBookItem> getPhoneBookItems() throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT id, firstName, lastName, phoneNumber, country, city FROM phone_book_item";
        List<PhoneBookItem> phoneBookItems = new ArrayList<>();
        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PhoneBookItem phoneBookItem = new PhoneBookItem();
                phoneBookItem.setId(resultSet.getLong("id"));
                phoneBookItem.setFirstName(resultSet.getString("firstName"));
                phoneBookItem.setLastName(resultSet.getString("lastName"));
                phoneBookItem.setPhoneNumber(resultSet.getString("phoneNumber"));
                phoneBookItem.setCountry(resultSet.getString("country"));
                phoneBookItem.setCity(resultSet.getString("city"));
                phoneBookItems.add(phoneBookItem);
            }
        }
        return phoneBookItems;
    }

    public GetPhoneBookItemRequest getPhoneBookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT firstName, lastName FROM phone_book_item WHERE id=?";
        GetPhoneBookItemRequest request1 = new GetPhoneBookItemRequest();
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                request1.setFirstName(resultSet.getString("firstName"));
                request1.setLastName(resultSet.getString("lastName"));
            }
            return request1;
        }
    }
}
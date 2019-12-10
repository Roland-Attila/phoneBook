package phoneBook.service;

import phoneBook.domain.PhoneBookItem;
import phoneBook.persistance.PhoneBookItemRepository;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.GetPhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PhoneBookItemService {

    private PhoneBookItemRepository phoneBookItemRepository = new PhoneBookItemRepository();

    public void createPhoneBookItem(CreatePhoneBookItemRequest request) throws SQLException, IOException,
            ClassNotFoundException {
        System.out.println("Creating phone-book-item: " + request);
        phoneBookItemRepository.createPhoneBookItem(request);
    }

    public void updatePhoneBookItem(long id, UpdatePhoneBookItemRequest request) throws SQLException, IOException,
            ClassNotFoundException {
        System.out.println("Updating phone-book-item: " + request);
        phoneBookItemRepository.updatePhoneBookItem(id, request);
    }

    public void deletePhoneBookItem(long id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting phone-book-item:" + id);
        phoneBookItemRepository.deletePhoneBookItem(id);
    }

    public void deletePhoneBookItems(long[] id) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting all phone-book-items: " + Arrays.toString(id));
        phoneBookItemRepository.deletePhoneBookItems(id);
    }

    public List<PhoneBookItem> getPhoneBookItems() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving phone-book-item: ");
        return phoneBookItemRepository.getPhoneBookItems();
    }

    public GetPhoneBookItemRequest getPhoneBookItem(long id) throws SQLException,
            IOException, ClassNotFoundException {
        System.out.println("Retrieving one item: " + id);
        return phoneBookItemRepository.getPhoneBookItem(id);
    }
}
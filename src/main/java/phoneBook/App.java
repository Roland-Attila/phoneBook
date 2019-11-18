package phoneBook;

import phoneBook.persistance.PhoneBookItemRepository;
import phoneBook.transfer.CreatePhoneBookItemRequest;
import phoneBook.transfer.UpdatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
//        UpdatePhoneBookItemRequest updatePhoneBookItemRequest = new UpdatePhoneBookItemRequest();
//        updatePhoneBookItemRequest.setFirstName("Johny");
//        updatePhoneBookItemRequest.setId(2);
////        request.setLastName("Piccolino");
////        request.setCountry("Italy");
////        request.setCity("Rome");
////        request.setPhoneNumber("+40427192027");
        PhoneBookItemRepository repository = new PhoneBookItemRepository();
        repository.getPhoneBookItems();
    }
}

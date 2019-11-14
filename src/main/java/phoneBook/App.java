package phoneBook;

import phoneBook.persistance.PhoneBookItemRepository;
import phoneBook.transfer.CreatePhoneBookItemRequest;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, IOException, ClassNotFoundException {
        CreatePhoneBookItemRequest request = new CreatePhoneBookItemRequest();
        request.setFirstName("Roberto");
        request.setLastName("Piccolino");
        request.setCountry("Italy");
        request.setCity("Rome");
        request.setPhoneNumber("+40427192027");
        PhoneBookItemRepository repository = new PhoneBookItemRepository();
        repository.createPhoneBookItem(request);
    }
}

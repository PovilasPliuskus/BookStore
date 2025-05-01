package RequestBodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookToPurchaseRequest {

    private Integer bookId;
    private Integer purchaseId;
}

import common.Type;
import handlers.Purchase;
import positions.Approver;
import positions.Manager;
import handlers.PurchaseValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Execution class of the application.
 * Be free to modify below line 14 (bellow comment line)
 */
public class PurchaseApprovalExecutor {

    public static void execute() {
        Approver manager = new Manager();
        ApprovalChainGenerator.generate(manager);
        //Be free to modify method below this line

        List<Purchase> purchases = new ArrayList<>();

        purchases.add(new Purchase(1, 15000, Type.CONSUMABLES));
        purchases.add(new Purchase(2, 5000, Type.PC));
        purchases.add(new Purchase(3, 5000, Type.PC));
        purchases.add(new Purchase(4, 3000, Type.CLERICAL));

        purchases.add(new Purchase(-5, 10, Type.PC)); // should not be valid
        purchases.add(new Purchase(5, -10000, Type.PC));  // should not be valid
        purchases.add(new Purchase(5, 10, null)); // should not be valid

        for (int i = 0; i < purchases.size(); i++) {
            try {
                Purchase purchase = purchases.get(i);
                PurchaseValidator validPurchase = new PurchaseValidator(purchase.getId(), purchase.getCost(),purchase.getType());
                manager.approve(validPurchase);
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }


}
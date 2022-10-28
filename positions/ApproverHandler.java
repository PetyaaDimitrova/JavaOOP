package positions;

import common.Type;
import handlers.LimitPrice;
import handlers.LimitPriceImpl;
import handlers.Purchase;
import handlers.PurchaseValidator;
import messages.ConstantMessages;

public class ApproverHandler extends Approver {
    protected Approver next;
    protected LimitPriceImpl limitPrice;

    public ApproverHandler() {
        this.limitPrice = new LimitPriceImpl();
    }
    @Override
    public void approve(PurchaseValidator purchase) {
        String className = this.getClass().getSimpleName();

        if (className.equals("ExecutiveMeeting")) {
            System.out.printf(ConstantMessages.PURCHASE_REQUIRES_APPROVAL_OF_EXECUTIVE_MEETING, purchase.getId(), purchase.getCost());
            return;
        }

        if (canApprove(purchase.getCost(), purchase.getType())) {
            System.out.printf(ConstantMessages.APPROVED_PURCHASE, className, purchase.getId(), purchase.getCost());
            return;
        }

        System.out.printf(ConstantMessages.PURCHASE_REQUIRES_APPROVAL_OF_HIGHER_POSITION, purchase.getId(), className);
        next.approve(purchase);
    }

    @Override
    protected boolean canApprove(double cost, Type type) {

        return cost <= limitPrice.getTargetPriceForGivenPosition(type, this.getClass().getSimpleName());
    }

    public Approver registerNext(Approver next) {
        this.next = next;
        return next;
    }
}

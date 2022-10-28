package positions;

import common.Type;
import handlers.PurchaseValidator;


public abstract class Approver {

    protected Approver next;

    public abstract void approve(PurchaseValidator purchase);
    protected abstract boolean canApprove(double cost, Type type);

    public Approver registerNext(Approver next) {
        this.next = next;
        return next;
    }

}

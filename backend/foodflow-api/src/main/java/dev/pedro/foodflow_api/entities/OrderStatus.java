package dev.pedro.foodflow_api.entities;

public enum OrderStatus {

    PENDING {
        public boolean canGoTo(OrderStatus newStatus) {
            return newStatus == IN_PREPARATION;
        }
    },
    IN_PREPARATION {
        public boolean canGoTo(OrderStatus newStatus) {
            return newStatus == READY;
        }
    },
    READY {
        public boolean canGoTo(OrderStatus newStatus) {
            return newStatus == DELIVERED;
        }
    },
    DELIVERED {
        public boolean canGoTo(OrderStatus newStatus) {
            return false;
        }
    };

    public abstract boolean canGoTo(OrderStatus newStatus);

}

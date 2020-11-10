package com.mapsa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LineitemOrderPK implements Serializable {
    private long orderId;
    private long lineitemId;

    @Column(name = "ORDER_ID")
    @Id
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "LINEITEM_ID")
    @Id
    public long getLineitemId() {
        return lineitemId;
    }

    public void setLineitemId(long lineitemId) {
        this.lineitemId = lineitemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineitemOrderPK that = (LineitemOrderPK) o;

        if (orderId != that.orderId) return false;
        if (lineitemId != that.lineitemId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (lineitemId ^ (lineitemId >>> 32));
        return result;
    }
}

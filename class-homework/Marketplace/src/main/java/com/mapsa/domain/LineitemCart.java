package com.mapsa.domain;

import javax.persistence.*;

@Entity
@Table(name = "LINEITEM_CART", schema = "MARKETPALCE", catalog = "")
@IdClass(LineitemCartPK.class)
public class LineitemCart {
    private long cartId;
    private long lineitemId;
    private String remarks;
    private long lockVersion;

    @Id
    @Column(name = "CART_ID")
    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    @Id
    @Column(name = "LINEITEM_ID")
    public long getLineitemId() {
        return lineitemId;
    }

    public void setLineitemId(long lineitemId) {
        this.lineitemId = lineitemId;
    }

    @Basic
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "LOCK_VERSION")
    public long getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(long lockVersion) {
        this.lockVersion = lockVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineitemCart that = (LineitemCart) o;

        if (cartId != that.cartId) return false;
        if (lineitemId != that.lineitemId) return false;
        if (lockVersion != that.lockVersion) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (int) (lineitemId ^ (lineitemId >>> 32));
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }
}

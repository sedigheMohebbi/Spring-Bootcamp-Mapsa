package com.mapsa.domain;

import javax.persistence.*;

@Entity
public class Payment {
    private long id;
    private String paymentNumber;
    private int status;
    private Double total;
    private String remarks;
    private long lockVersion;
    private Bill billByBillId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PAYMENT_NUMBER")
    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    @Basic
    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "TOTAL")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (status != payment.status) return false;
        if (lockVersion != payment.lockVersion) return false;
        if (paymentNumber != null ? !paymentNumber.equals(payment.paymentNumber) : payment.paymentNumber != null)
            return false;
        if (total != null ? !total.equals(payment.total) : payment.total != null) return false;
        if (remarks != null ? !remarks.equals(payment.remarks) : payment.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (paymentNumber != null ? paymentNumber.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "BILL_ID", referencedColumnName = "ID", nullable = false)
    public Bill getBillByBillId() {
        return billByBillId;
    }

    public void setBillByBillId(Bill billByBillId) {
        this.billByBillId = billByBillId;
    }
}

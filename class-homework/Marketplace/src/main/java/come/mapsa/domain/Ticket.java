package come.mapsa.domain;

import javax.persistence.*;

@Entity
public class Ticket {
    private long id;
    private String ticketNumber;
    private long status;
    private String remarks;
    private long lockVersion;
    private Lineitem lineitemByLineitemId;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TICKET_NUMBER")
    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Basic
    @Column(name = "STATUS")
    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (status != ticket.status) return false;
        if (lockVersion != ticket.lockVersion) return false;
        if (ticketNumber != null ? !ticketNumber.equals(ticket.ticketNumber) : ticket.ticketNumber != null)
            return false;
        if (remarks != null ? !remarks.equals(ticket.remarks) : ticket.remarks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (ticketNumber != null ? ticketNumber.hashCode() : 0);
        result = 31 * result + (int) (status ^ (status >>> 32));
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (int) (lockVersion ^ (lockVersion >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "LINEITEM_ID", referencedColumnName = "ID", nullable = false)
    public Lineitem getLineitemByLineitemId() {
        return lineitemByLineitemId;
    }

    public void setLineitemByLineitemId(Lineitem lineitemByLineitemId) {
        this.lineitemByLineitemId = lineitemByLineitemId;
    }
}

package ru.aurumtask.shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "transport", schema = "transport")
public class TransportEntity implements Serializable {
    private int idtransport;
    private String stateNumber;
    private String uin;
    private String parkname;

    @Id
    @Column(name = "idtransport", nullable = false)
    public int getIdtransport() {
        return idtransport;
    }

    public void setIdtransport(int idtransport) {
        this.idtransport = idtransport;
    }

    @Basic
    @Column(name = "state_number", nullable = false, length = 45)
    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    @Basic
    @Column(name = "uin", nullable = false, length = 45)
    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    @Basic
    @Column(name = "parkname", nullable = false, length = 45)
    public String getParkname() {
        return parkname;
    }

    public void setParkname(String parkname) {
        this.parkname = parkname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportEntity that = (TransportEntity) o;
        return idtransport == that.idtransport &&
                Objects.equals(stateNumber, that.stateNumber) &&
                Objects.equals(uin, that.uin) &&
                Objects.equals(parkname, that.parkname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtransport, stateNumber, uin, parkname);
    }
}

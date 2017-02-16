package edu.nju.data.entity;

import edu.nju.util.enums.CheckState;
import edu.nju.util.enums.PayWay;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Check entity
 */
@Entity
@Table(name = "check_record", schema = "hostel", catalog = "")
public class CheckRecordEntity {
    private int id;
    private CheckState state;
    private PayWay payway;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Date start;
    private Date end;
    private RoomEntity roomEntity;
    private MemberEntity memberEntity;
    private List<TenantEntity> tenantEntities;
    private Integer pay;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomid",referencedColumnName = "id")
    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    @ManyToOne
    @JoinColumn(name = "memberid",referencedColumnName = "id")
    public MemberEntity getMemberEntity() {
        return memberEntity;
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    @ManyToMany
    @JoinTable(name = "check_tenant", schema = "hostel",
            joinColumns = @JoinColumn(name = "check_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tenant_id", referencedColumnName = "id"))
    public List<TenantEntity> getTenantEntities() {
        return tenantEntities;
    }

    public void setTenantEntities(List<TenantEntity> tenantEntities) {
        this.tenantEntities = tenantEntities;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    public CheckState getState() {
        return state;
    }

    public void setState(CheckState state) {
        this.state = state;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "payway")
    public PayWay getPayway() {
        return payway;
    }

    public void setPayway(PayWay payway) {
        this.payway = payway;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "update_at")
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "start")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckRecordEntity that = (CheckRecordEntity) o;

        if (id != that.id) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (payway != null ? !payway.equals(that.payway) : that.payway != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        if (end != null ? !end.equals(that.end) : that.end != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (payway != null ? payway.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "pay")
    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }
}

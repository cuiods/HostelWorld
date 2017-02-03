package edu.nju.data.entity;

import edu.nju.util.enums.Gender;
import edu.nju.util.enums.UserType;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * basic user entity.<br/>
 * delete strategy: ignore entity if {@code deletedAt} is NOT null.
 */
@Entity
@Table(name = "user")
@Where(clause="deleted_at is null")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String avatar;
    private Gender gender;
    private UserType type;
    private Timestamp createdAt;
    private Timestamp updateAt;
    private Timestamp deletedAt;
    private List<AuthorityEntity> authorityEntities;
    private List<AccountEntity> accountEntities;
    private List<MessageEntity> messageEntities;
    private byte valid;

    /**
     * the database generates the key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", schema = "hostel",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "auth_id", referencedColumnName = "id",nullable = false))
    public List<AuthorityEntity> getAuthorityEntities() {
        return authorityEntities;
    }

    public void setAuthorityEntities(List<AuthorityEntity> authorityEntities) {
        this.authorityEntities = authorityEntities;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    public List<AccountEntity> getAccountEntities() {
        return accountEntities;
    }

    public void setAccountEntities(List<AccountEntity> accountEntities) {
        this.accountEntities = accountEntities;
    }

    @OneToMany
    @JoinColumn(name = "receive")
    public List<MessageEntity> getMessageEntities() {
        return messageEntities;
    }

    public void setMessageEntities(List<MessageEntity> messageEntities) {
        this.messageEntities = messageEntities;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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
    @Column(name = "deleted_at")
    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "valid")
    public byte getValid() {
        return valid;
    }

    public void setValid(byte valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (valid != that.valid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (gender != that.gender) return false;
        if (type != that.type) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updateAt != null ? !updateAt.equals(that.updateAt) : that.updateAt != null) return false;
        if (deletedAt != null ? !deletedAt.equals(that.deletedAt) : that.deletedAt != null) return false;
        if (authorityEntities != null ? !authorityEntities.equals(that.authorityEntities) : that.authorityEntities != null)
            return false;
        return accountEntities != null ? accountEntities.equals(that.accountEntities) : that.accountEntities == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (deletedAt != null ? deletedAt.hashCode() : 0);
        result = 31 * result + (authorityEntities != null ? authorityEntities.hashCode() : 0);
        result = 31 * result + (accountEntities != null ? accountEntities.hashCode() : 0);
        result = 31 * result + (int) valid;
        return result;
    }

}

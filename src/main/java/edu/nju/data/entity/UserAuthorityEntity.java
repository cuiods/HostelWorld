package edu.nju.data.entity;

import javax.persistence.*;

/**
 * Created by cuihao on 2017/1/26.
 */
@Entity
@Table(name = "user_authority", schema = "hostel", catalog = "")
@IdClass(UserAuthorityEntityPK.class)
public class UserAuthorityEntity {
    private int userId;
    private int authId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "auth_id")
    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorityEntity that = (UserAuthorityEntity) o;

        if (userId != that.userId) return false;
        if (authId != that.authId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + authId;
        return result;
    }
}

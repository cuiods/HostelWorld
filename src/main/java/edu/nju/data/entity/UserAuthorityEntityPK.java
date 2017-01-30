package edu.nju.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cuihao on 2017/1/26.
 */
public class UserAuthorityEntityPK implements Serializable {
    private int userId;
    private int authId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "auth_id")
    @Id
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

        UserAuthorityEntityPK that = (UserAuthorityEntityPK) o;

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

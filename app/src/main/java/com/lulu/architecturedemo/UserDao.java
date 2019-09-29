package com.lulu.architecturedemo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author zhanglulu on 2019/9/26.
 * for
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUsers(User users);

    @Query("SELECT * FROM users")
    Flowable<List<User>> loadAllUsers();
}

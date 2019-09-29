package com.lulu.architecturedemo;
import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author zhanglulu on 2019/9/26.
 * for
 */
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

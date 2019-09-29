package com.lulu.architecturedemo;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhanglulu on 2019/9/26.
 * for
 */
public class UserHandle {

    private static volatile UserHandle instance; // volatile 防止指令重排序
    private final RoomDatabase.Builder<UserDatabase> db;

    private UserHandle() {
        db = Room.databaseBuilder(App.getInstance(), UserDatabase.class, "user.db");
    }

    public static UserHandle getInstance() {
        if (instance == null) {
            synchronized (UserHandle.class) {
                if (instance == null) {
                    instance = new UserHandle();
                }
            }
        }
        return instance;
    }

    public Observable<Long> insert(final User user) {
        return Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Long longs = db.build().userDao().insertUsers(user);
                emitter.onNext(longs);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<User>> getAllUser() {
        return db.build().userDao().loadAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



}

package com.lulu.architecturedemo;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @author zhanglulu on 2019/9/26.
 * for
 */
@Entity(tableName = "users", indices = {@Index(value = {"firstName"},unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String firstName;
    public String lastName;
}

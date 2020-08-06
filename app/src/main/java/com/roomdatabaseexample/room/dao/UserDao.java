package com.roomdatabaseexample.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.roomdatabaseexample.room.entity.UserDetaill;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserDetaill")
    List<UserDetaill> getAll();

    @Query("SELECT * FROM UserDetaill WHERE uid = :uid")
    List<UserDetaill> selectOne(int uid);

    @Insert
    void insertId(UserDetaill userDetaill);

    @Query("DELETE FROM UserDetaill")
    void deleteAll();

    @Delete
    void delete(UserDetaill... userDetaills);
}

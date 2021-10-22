package com.example.segundoparcial.daoMC;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.segundoparcial.modelMC.ContactsMC;
import java.util.List;

@Dao
public interface ContactsMCDao {
    @Query("select * from contactos")
    public List<ContactsMC> getAll();

    @Query("select * from contactos where id = :id")
    public ContactsMC get(int id);

    @Insert
    public void save(ContactsMC entity);

    @Delete
    public void delete(ContactsMC entity);

    @Update
    public void update(ContactsMC entity);
}

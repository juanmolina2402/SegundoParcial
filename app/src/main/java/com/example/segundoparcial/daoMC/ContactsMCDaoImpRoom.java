package com.example.segundoparcial.daoMC;

import android.content.Context;

import androidx.room.Room;

import com.example.segundoparcial.dataMC.DataBaseRoomMC;
import com.example.segundoparcial.modelMC.ContactsMC;
import java.util.List;

public class ContactsMCDaoImpRoom implements ContactsMCDao{
    private DataBaseRoomMC dbr;
    private ContactsMCDao dao;

    public ContactsMCDaoImpRoom(Context context){
        dbr = Room.databaseBuilder(context,DataBaseRoomMC.class,"db").allowMainThreadQueries().build();
        dao = dbr.contactsMCDao();
    }

    @Override
    public List<ContactsMC> getAll() {
        return dao.getAll();
    }

    @Override
    public ContactsMC get(int id) {
        return dao.get(id);
    }

    @Override
    public void save(ContactsMC entity) {
        dao.save(entity);
    }

    @Override
    public void delete(ContactsMC entity) {
        dao.delete(entity);
    }

    @Override
    public void update(ContactsMC entity) {
        dao.update(entity);
    }
}

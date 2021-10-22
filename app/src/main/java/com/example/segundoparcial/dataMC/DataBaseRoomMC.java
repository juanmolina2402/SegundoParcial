package com.example.segundoparcial.dataMC;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.segundoparcial.daoMC.ContactsMCDao;
import com.example.segundoparcial.modelMC.ContactsMC;

@Database(entities = {ContactsMC.class}, version = 1)
public abstract class DataBaseRoomMC extends RoomDatabase{
    public abstract ContactsMCDao contactsMCDao();
}

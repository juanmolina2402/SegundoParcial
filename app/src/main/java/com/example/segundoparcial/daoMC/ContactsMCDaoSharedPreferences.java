package com.example.segundoparcial.daoMC;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.segundoparcial.dataMC.SharedPreferenceConfigMC;
import com.example.segundoparcial.modelMC.ContactsMC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactsMCDaoSharedPreferences implements ContactsMCDao{
    private SharedPreferenceConfigMC config;
    private int c;

    public ContactsMCDaoSharedPreferences(Context context) {
        config = new SharedPreferenceConfigMC(context);
        c = config.getPreferences().getInt("contador",0);
    }

    @Override
    public List<ContactsMC> getAll() {
        List<ContactsMC> list = new ArrayList<ContactsMC>();

        for (int i = 1; i <= c; i++) {
            ContactsMC contactsMC = new ContactsMC();
            try{
                Set<String> p = config.getPreferences().getStringSet("propietario"+"numero"+"contacto"+i,new HashSet<>());
                String[] items= p.toArray(new String[p.size()]);
                contactsMC.setId(Integer.parseInt(items[3]));
                contactsMC.setNombre(items[2]);
                contactsMC.setNumero(items[1]);
                contactsMC.setPropietario(items[0]);
                list.add(contactsMC);
            }catch (Exception e){
            }
        }
        return list;
    }

    @Override
    public ContactsMC get(int id) {
        return null;
    }

    @Override
    public void save(ContactsMC entity) {
        SharedPreferences.Editor editor= config.getPreferences().edit();
        c++;

        entity.setId(c);
        Set<String> contacts = new HashSet<>();
        contacts.add(""+entity.getId());
        contacts.add(entity.getNombre());
        contacts.add(entity.getNumero());
        contacts.add(entity.getPropietario());
        editor.putStringSet("contacto"+c, contacts);
        editor.putStringSet("numero"+c, contacts);
        editor.putStringSet("propietario"+c, contacts);
        editor.putInt("contador", c);
        editor.commit();
    }

    @Override
    public void delete(ContactsMC entity) {
    }

    @Override
    public void update(ContactsMC entity) {
    }
}

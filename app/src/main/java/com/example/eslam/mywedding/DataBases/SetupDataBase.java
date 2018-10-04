package com.example.eslam.mywedding.DataBases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.eslam.mywedding.Models.ContryModel.Country_;
import com.example.eslam.mywedding.Models.HallsModels.Hall;
import com.example.eslam.mywedding.Models.HallsModels.ListHallsModles;
import com.example.eslam.mywedding.Models.ImageModel;

@Database(entities = {Hall.class, ImageModel.class, Country_.class, ListHallsModles.class}, version = 1, exportSchema = false)
@TypeConverters({ImageConverts.class,HallsConverts.class})
public abstract class SetupDataBase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "baking";
    private static SetupDataBase sINSTANCE;

    public static SetupDataBase getINSTANCE(Context context) {
        if (sINSTANCE == null) {
            synchronized (LOCK) {
                sINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        SetupDataBase.class,
                        SetupDataBase.DATABASE_NAME)
                        .build();
            }
        }
        return sINSTANCE;
    }


    public abstract SetupDao daoHalls();

}

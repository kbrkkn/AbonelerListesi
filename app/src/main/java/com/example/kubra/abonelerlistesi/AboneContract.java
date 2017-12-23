package com.example.kubra.abonelerlistesi;


import android.provider.BaseColumns;

public class AboneContract {

    public static final class AboneEntry implements BaseColumns {
        public static final String TABLE_NAME = "abonelist";
        public static final String COLUMN_ABONE_NAME = "abone_name";
        public static final String COLUMN_EMAIL = "email";

    }
}

package com.example.kubra.abonelerlistesi;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.AboneViewHolder>{
    private Cursor aboneListesi;

    public Adapter(Cursor aboneListesi){
        this.aboneListesi=aboneListesi;
    }

    class AboneViewHolder extends RecyclerView.ViewHolder{
        TextView isimTv,emailTv;
        public AboneViewHolder(View itemView) {
            super(itemView);
            isimTv= (TextView) itemView.findViewById(R.id.textViewName);
            emailTv= (TextView) itemView.findViewById(R.id.textViewEmail);
        }

    }
    @Override
    public AboneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView=layoutInflater.inflate(R.layout.satir,parent,false);
        return new AboneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AboneViewHolder holder, int position) {
if(!aboneListesi.moveToPosition(position)){return;}

        int isimColIndex=aboneListesi.getColumnIndex(AboneContract.AboneEntry.COLUMN_ABONE_NAME);
        String isim=aboneListesi.getString(isimColIndex);

        int emailColIndex=aboneListesi.getColumnIndex(AboneContract.AboneEntry.COLUMN_EMAIL);
        String email=aboneListesi.getString(emailColIndex);

        long id=aboneListesi.getLong(aboneListesi.getColumnIndex(AboneContract.AboneEntry._ID));

        holder.itemView.setTag(id);
        holder.isimTv.setText(isim);
        holder.emailTv.setText(email);

    }

    @Override
    public int getItemCount() {
        Log.i("count",""+aboneListesi.getCount());
        return aboneListesi.getCount();

    }

    public void listeyiGüncelle(Cursor yeniListe) {
        if (aboneListesi != null) aboneListesi.close();
       aboneListesi=yeniListe;
        if (yeniListe != null) {
            this.notifyDataSetChanged();
        }
    }

}

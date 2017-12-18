package com.alexisholyoak.ejercicio2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alexisholyoak on 16/12/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerItemViewHolder>{
    private ArrayList<Persona> myList;
    int mLastPosition=0;
    private RemoveClickListner mListner;

    public RecyclerAdapter(ArrayList<Persona> myList,RemoveClickListner listner) {
        this.myList = myList;
        mListner=listner;
    }
    public RecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persondata, parent, false);
        RecyclerItemViewHolder holder = new RecyclerItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerItemViewHolder holder, int position) {
        Log.d("onBindViewHoler ", myList.size() + "");
        holder.nombre.setText(myList.get(position).getNombre());
        mLastPosition =position;

    }

    @Override
    public int getItemCount() {
        return(null != myList?myList.size():0);
    }
    public void notifyData(ArrayList<Persona> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyDataSetChanged();
    }
    public class RecyclerItemViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private final TextView nombre;
        private final Button eliminar;
        public RecyclerItemViewHolder(final View parent){
            super(parent);
            parent.setOnClickListener(this);
            nombre=(TextView)parent.findViewById(R.id.pnombre);
            eliminar=(Button)parent.findViewById(R.id.deletebtn);
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListner.OnRemoveClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }
    private RecyclerAdapter.OnEntryClickListener mOnEntryClickListener;
    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }
    public void setOnEntryClickListener(RecyclerAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}

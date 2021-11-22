package com.example.mycontactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter {

    private ArrayList<Contact> contactData;
    private View.OnClickListener onClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewContact;
        public TextView textViewCity;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getContactTextView() {
            return textViewContact;
        }
        public TextView getCityTextView() {return textViewCity;}
    }

    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    public ContactAdapter(ArrayList<Contact> arrayList){
        contactData = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);


        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        cvh.getContactTextView().setText(contactData.get(position).getName());
        cvh.getCityTextView().setText(contactData.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }
}

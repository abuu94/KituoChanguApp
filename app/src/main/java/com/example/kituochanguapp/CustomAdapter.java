package com.example.kituochanguapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Data> mUploads;
    private OnItemClickListener mListener;

    public CustomAdapter(Context mContext, List<Data> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Data uploadCurrent = mUploads.get(position);

        holder.mTvSheha.setText(uploadCurrent.getMysheha());
        holder.mTvKituo.setText(uploadCurrent.getMystation());
        holder.mTvLat.setText(uploadCurrent.getMyXcordina());
        holder.mTvLong.setText(uploadCurrent.getMyYcordina());


        holder.mTvDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
                builder.setTitle("Kufuta Data");
                builder.setMessage("Je unatakaka kufuta data ?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Station/"+uploadCurrent.getTime());
                        ref.removeValue();
                        message("Umfanikiwa",  " Record imefutwa kwa usahihi");
                    }
                });
                builder.create().show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView mTvSheha,mTvKituo,mTvLat,mTvLong,mTvDelete;

       // ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            mTvSheha = itemView.findViewById(R.id.tv_sheha);
            mTvKituo = itemView.findViewById(R.id.tv_kituo);
            mTvLat=itemView.findViewById(R.id.tv_lat);
            mTvLong=itemView.findViewById(R.id.tv_long);
            mTvDelete=itemView.findViewById(R.id.tv_delete);


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener!=null){
                //Get the position of the clicked item
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }
        // Handle Menu Items
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1,"Verify code");
            MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");
            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener!=null){
                //Get the position of the clicked item
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            mListener.onVerifyClick(position);
                            return true;
                        case 2:
                            mListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);

        void onVerifyClick(int position);

        void onDeleteClick(int position);
    }
    public void message(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public void setSearchOperation(List<Data> newList){
        mUploads = new ArrayList<>();
        mUploads.addAll(newList);
        notifyDataSetChanged();
    }
}
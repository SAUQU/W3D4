package com.example.segundoauqui.w3d4;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.segundoauqui.w3d4.model.Item;

import java.util.ArrayList;

/**
 * Created by segundoauqui on 8/18/17.
 */

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.ViewHolder>{


    ArrayList<Item> fromRetrotiAdapterArrayList = new ArrayList<>();
    Context context;

    public RetrofitAdapter(ArrayList<Item> fromRetrotiAdapterArrayList){
        this.fromRetrotiAdapterArrayList = fromRetrotiAdapterArrayList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvText1;
        TextView tvText2;
        TextView tvText3;
        ImageView ivImage;
        ImageView ivSmall;


        public ViewHolder(View itemView) {
            super(itemView);

            ivSmall = (ImageView) itemView.findViewById(R.id.ivSmall);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvText1 = (TextView) itemView.findViewById(R.id.tvText1);
            tvText2 = (TextView) itemView.findViewById(R.id.tvText2);
            tvText3 = (TextView) itemView.findViewById(R.id.tvText3);
        }
    }

    @Override
    public RetrofitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_helper, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RetrofitAdapter.ViewHolder holder, final int position) {


        final Item retro = fromRetrotiAdapterArrayList.get(position);

        holder.tvText1.setText(retro.getClass() + retro.getDateTaken());
        holder.tvText2.setText(retro.getTitle());
        holder.tvText3.setText(retro.getPublished());
        Glide.with(holder.itemView.getContext()).load(retro.getMedia().getM()).into(holder.ivImage);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Select One From The Options")
                        .setNegativeButton("Show Small Image", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Dialog dialogCustom = new Dialog(v.getContext());
                                dialogCustom.setContentView(R.layout.smallpix);
                                ImageView largePix = (ImageView) dialogCustom.findViewById(R.id.ivSmall);
                                Glide.with(v.getContext()).load(retro.getMedia().getM()).into(largePix);
                                dialogCustom.show();
                                Toast.makeText(context, "Showing Small Image", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setPositiveButton("Show Full Image", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(v.getContext(), ShowPix.class);
                                intent.putExtra("image",retro.getMedia().getM());
                                v.getContext().startActivity(intent);
                                Toast.makeText(context, "Showing Full Image", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return fromRetrotiAdapterArrayList.size();
    }


}

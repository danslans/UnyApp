package com.darkcode.unyapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkcode.unyapp.R;
import com.darkcode.unyapp.model.University;

import java.util.ArrayList;

/**
 * Created by daniel.gomez on 31/08/2017.
 */

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.Holder> {
    private ArrayList<University> arrayList;
    private Context context;

    public UniversityAdapter(ArrayList<University> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public UniversityAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.card_uny_adapter,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(UniversityAdapter.Holder holder, int position) {
        holder.information.setText(arrayList.get(position).getInformation());
        holder.name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView information;
        public TextView name;
        public ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            information=(TextView)itemView.findViewById(R.id.txtInfoUny);
            name=(TextView)itemView.findViewById(R.id.txtNameUny);
            imageView=(ImageView)itemView.findViewById(R.id.imgUny);
        }
    }
}

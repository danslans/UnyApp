package com.darkcode.unyapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkcode.unyapp.InformationUny;
import com.darkcode.unyapp.R;
import com.darkcode.unyapp.model.University;

import java.util.ArrayList;
import android.support.v7.widget.*;
import android.widget.*;
import android.animation.*;
import android.view.animation.*;

/**
 * Created by daniel.gomez on 31/08/2017.
 */

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.Holder> {
    private ArrayList<University> arrayList;
    private Context context;
	private Animation a;

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
    public void onBindViewHolder(UniversityAdapter.Holder holder,final int position) {
        holder.information.setText(arrayList.get(position).getInformation());
        holder.name.setText(arrayList.get(position).getName());
		holder.id.setText(String.valueOf( arrayList.get(position).getId()));
		//a=AnimationUtils.loadAnimation(context,R.anim.card_anim);
		holder.cardView.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
                Intent intent=new Intent(context, InformationUny.class);
                intent.putExtra("idUny",arrayList.get(position).getId());
                context.startActivity(intent);
			}
		});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView information;
        public TextView name;
		public TextView id;
        public ImageView imageView;
		public CardView cardView;
        public Holder(View itemView) {
            super(itemView);
            information=(TextView)itemView.findViewById(R.id.txtInfoUny);
            name=(TextView)itemView.findViewById(R.id.txtNameUny);
			id=(TextView)itemView.findViewById(R.id.txtIdUny);
            imageView=(ImageView)itemView.findViewById(R.id.imgUny);
			cardView=(CardView)itemView.findViewById(R.id.cardUny);
        }
    }
}

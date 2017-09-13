package com.darkcode.unyapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
	//private Animation a;
    private ValueAnimator valueAnimator;

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
        loadTabs(holder.tabHost);
        /**Animacion **/
        int red = 0xFFD50000;
        int blue= 0xFF64DD17;
        valueAnimator= ObjectAnimator.ofInt(holder.imgUny,"backgroundColor",red,blue);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
         /****/

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

    private void loadTabs(TabHost tabHost){

        tabHost.setup();
        tabHost.setBackgroundColor(Color.BLUE);
        TabHost.TabSpec spec =tabHost.newTabSpec("hola");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Information");
        tabHost.addTab(spec);

        spec.setContent(R.id.tab2);
        spec.setIndicator("Dates");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
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
        public LinearLayout imgUny;
		public CardView cardView;
        public TabHost tabHost;

        public Holder(View itemView) {
            super(itemView);
            information=(TextView)itemView.findViewById(R.id.txtInfoUny);
            name=(TextView)itemView.findViewById(R.id.txtNameUny);
			id=(TextView)itemView.findViewById(R.id.txtIdUny);
            imgUny =(LinearLayout)itemView.findViewById(R.id.imgUny);
            tabHost=(TabHost)itemView.findViewById(R.id.tabHost);
//            imageView=(ImageView)itemView.findViewById(R.id.imgUny);
			cardView=(CardView)itemView.findViewById(R.id.cardUny);
        }
    }
}

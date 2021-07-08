package com.cs446.covidsafe.ui.Vaccines.VaccineInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cs446.covidsafe.R;

import java.util.List;

public class vaccine_info_adapter extends RecyclerView.Adapter<vaccine_info_adapter.ViewHolder>{
    List<vaccine_desc> data;
    //Context context;
    public vaccine_info_adapter(List<vaccine_desc> data) {
        this.data = data;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.vh_vaccine_info_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull vaccine_info_adapter.ViewHolder holder, int position) {
        vaccine_desc vd = data.get(position);
        holder.t.setText(vd.getV_type());
        holder.d.setText(vd.getV_desc());
        Boolean isExpandable = data.get(position).getExpandable();
        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t, d; // type and desc
        RelativeLayout expandableLayout;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.type);
            d = itemView.findViewById(R.id.description);

            constraintLayout = itemView.findViewById(R.id.vacine_info_contraintLayout);
            expandableLayout = itemView.findViewById(R.id.expandable);

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vaccine_desc vd = data.get(getAdapterPosition());
                    vd.setExpandable(!vd.getExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}

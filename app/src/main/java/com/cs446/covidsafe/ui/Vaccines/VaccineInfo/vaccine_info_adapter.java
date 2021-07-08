package com.cs446.covidsafe.ui.Vaccines.VaccineInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cs446.covidsafe.R;

public class vaccine_info_adapter extends RecyclerView.Adapter<vaccine_info_adapter.ViewHolder>{
    String types[], desc[];
    Context context;
    public vaccine_info_adapter(Context ct, String[] types, String[] desc) {
        context = ct;
        this.types = types;
        this.desc = desc;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vh_vaccine_info_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull vaccine_info_adapter.ViewHolder holder, int position) {
        holder.t.setText(types[position]);
        holder.d.setText(desc[position]);
    }

    @Override
    public int getItemCount() {
        return types.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView t, d; // type and desc

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.type);
            d = itemView.findViewById(R.id.description);
        }
    }
}

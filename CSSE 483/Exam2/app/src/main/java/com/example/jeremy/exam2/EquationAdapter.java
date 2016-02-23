package com.example.jeremy.exam2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 1/9/2016.
 */
public class EquationAdapter extends RecyclerView.Adapter<EquationAdapter.ViewHolder> {

    public List<Equation> equationList;
    public List<Equation> displayedList;
    private Context context;
    private int hintCounter;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView equation;

        public ViewHolder(View itemView) {
            super(itemView);
            equation = (TextView) itemView.findViewById(R.id.equation);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            setEquationAnswer(getAdapterPosition(), equation);
            Equation eq = displayedList.get(getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equation_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Equation eq = this.displayedList.get(position);
        holder.equation.setText(eq.toString());
    }

    @Override
    public int getItemCount() {
        return this.displayedList.size();
    }


    public EquationAdapter(List<Equation> equationList, List<Equation> displayedList, Context context) {
        this.displayedList = displayedList;
        this.equationList = equationList;
        this.hintCounter = 0;
        this.context = context;
    }

    public int getHintCounter() {
        return hintCounter;
    }


    public void setEquationAnswer(int position, TextView textView) {
        Equation eq = this.displayedList.get(position);
        eq.setDisplayingAnswer(!eq.isDisplayingAnswer());
        notifyItemChanged(position);


        // The values here are intentionally inverted. A strange issue is occurring on my phone where the sizes are inverting despite my code
        if(eq.isDisplayingAnswer()) {
            textView.setTextSize(40);
        } else {
            hintCounter++;
            textView.setTextSize(35);
        }
    }

    public Equation removeEquation(int position) {
        Equation eq = this.displayedList.remove(position);
        if(this.displayedList.isEmpty() && this.equationList.isEmpty()) {
            Intent congratsIntent = new Intent(this.context, CongratsActivity.class);
            congratsIntent.putExtra("Hints", this.hintCounter);
            this.context.startActivity(congratsIntent);

        }
        notifyItemRemoved(position);
        return eq;
    }

    public void addToPool(Equation eq) {
        this.equationList.add(eq);
    }
}

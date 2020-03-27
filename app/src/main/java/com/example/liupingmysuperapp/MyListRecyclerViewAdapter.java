package com.example.liupingmysuperapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.liupingmysuperapp.ListFragment.OnListFragmentInteractionListener;

import org.jetbrains.annotations.NotNull;

public class MyListRecyclerViewAdapter extends RecyclerView.Adapter<MyListRecyclerViewAdapter.ViewHolder> {

    private final ListCatFacts listCatFacts;
    private final OnListFragmentInteractionListener mListener;

    public MyListRecyclerViewAdapter(ListCatFacts items, OnListFragmentInteractionListener listener) {
        listCatFacts = items;
        mListener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fact_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = listCatFacts.data[position];
        holder.mContentView.setText(listCatFacts.data[position].fact);

        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCatFacts.data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;
        public CatFact mItem;

        public ViewHolder(View view) {
            super(view);

            mContentView = (TextView) view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

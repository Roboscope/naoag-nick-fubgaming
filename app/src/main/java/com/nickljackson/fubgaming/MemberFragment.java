package com.nickljackson.fubgaming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nickljackson.fubgaming.Logic.Controller;
import com.nickljackson.fubgaming.data.ListItem;
import com.nickljackson.fubgaming.data.ListItemDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment implements ViewInterface{

    private ArrayList<ListItem> listOfData = new ArrayList<>();

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Controller controller;


    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutInflater = inflater;
        View view = layoutInflater.inflate(R.layout.fragment_member, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_member);

        controller = new Controller(this, new ListItemDataSource(getContext(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void updateAdapterAndView(ArrayList<ListItem> pListOfData) {
        this.listOfData = pListOfData;
        this.adapter.notifyDataSetChanged();
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v  = layoutInflater.inflate(R.layout.item_member, parent, false);


            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            ListItem currentItem = listOfData.get(position);

            holder.avatar.setBaseline(3);
            holder.name.setText(
                    currentItem.getName()
            );
            holder.status.setText(
                    currentItem.getStatus()
            );
        }

        @Override
        public int getItemCount() {
            return listOfData.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView avatar;
            private TextView name;
            private TextView status;
            private ViewGroup container;


            public CustomViewHolder(View itemView){
                super(itemView);

                this.avatar = (ImageView) itemView.findViewById(R.id.item_image);
                this.name = (TextView) itemView.findViewById(R.id.member_name);
                this.status = (TextView) itemView.findViewById(R.id.member_status);
                this.container = (ViewGroup) itemView.findViewById(R.id.list_item);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                ListItem listItem = listOfData.get(
                        this.getAdapterPosition()
                );

                controller.onListItemClick(
                        listItem
                );
            }
        }
    }
}

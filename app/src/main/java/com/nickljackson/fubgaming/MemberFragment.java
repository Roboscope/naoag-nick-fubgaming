package com.nickljackson.fubgaming;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends Fragment implements ViewInterface{

    private ArrayList<ListItem> listOfData = new ArrayList<>();

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListItemDataSource dataSource;

    private Controller controller;

    public MemberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutInflater = inflater;
        final View view = layoutInflater.inflate(R.layout.fragment_member, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_member);

        controller = new Controller(this, new ListItemDataSource(getContext(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener(){
                    @Override
                    public void onRefresh() {
                        if(isOnline()) {
                            controller.update();
                        } else {
                            updateAdapterAndView(SaveSharedPreferences.getMemberList(getContext()));
                        }
                    }
                }
        );
        return view;

    }

    @Override
    public void updateAdapterAndView(ArrayList<ListItem> pListOfData) {
        this.listOfData = pListOfData;
        saveList(pListOfData);
        this.adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
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
            Picasso.with(getContext()).load(currentItem.getAvatar()).into(holder.avatar);
            holder.name.setText(
                    currentItem.getName()
            );
            holder.status.setText(
                    currentItem.getStatus()
            );

        }

        @Override
        public int getItemCount()  {
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
                this.avatar = (ImageView) itemView.findViewById(R.id.item_image);
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
    private void saveList(ArrayList<ListItem> pList) {
        ArrayList<ListItem> temp = new ArrayList<>();
        for(int i = 0; i < pList.size(); i++) {
            ListItem l = pList.get(i);
            temp.add(new ListItem("no connection", l.getName(), l.getAvatar()));
        }
        SaveSharedPreferences.setMemberList(getContext(),temp);

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}

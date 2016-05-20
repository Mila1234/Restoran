package com.example.marijaradisavljevic.restoran.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marijaradisavljevic.restoran.R;
import com.example.marijaradisavljevic.restoran.activiry.ActivityFirst;
import com.example.marijaradisavljevic.restoran.adapters.HolderAdapterItem;
import com.example.marijaradisavljevic.restoran.database.Rezervation;
import com.example.marijaradisavljevic.restoran.adapters.MyCustomAdatperForTheList;
import com.example.marijaradisavljevic.restoran.database.AdapterDB;

import java.util.ArrayList;

/**
 * Created by marija.radisavljevic on 5/13/2016.
 */
public class FragmentListReservations extends Fragment {


    ListView lvDetail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.list_rezervations, container, false);

        lvDetail = (ListView)mRoot.findViewById(R.id.list_reservations);


        lvDetail.setOnTouchListener(new ListView.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int action = event.getAction();
                        switch (action) {
                            case MotionEvent.ACTION_DOWN:
                                // Disallow ScrollView to intercept touch events.
                                v.getParent().requestDisallowInterceptTouchEvent(true);
                                break;

                            case MotionEvent.ACTION_UP:
                                // Allow ScrollView to intercept touch events.
                                v.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }

                        // Handle ListView touch events.
                        v.onTouchEvent(event);
                        return true;
                    }
                });

        MyCustomAdatperForTheList<ItemForRezervationsList> adapter = new MyCustomAdatperForTheList(getActivity());
        ArrayList<Rezervation> myList = AdapterDB.getInstance().getRezervations();
        adapter.addItem(new ItemForRezervationsList(myList.get(0)));
        adapter.addItem(new ItemForRezervationsList(myList.get(1)));
        adapter.addItem(new ItemForRezervationsList(myList.get(2)));



        lvDetail.setAdapter(adapter);

        return mRoot;
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_logout).setVisible(true);
        menu.findItem(R.id.action_user_info).setVisible(true);
        menu.findItem(R.id.action_add).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }


    public class ItemForRezervationsList extends HolderAdapterItem {

        //private String time,name_user,numberTable,price,itemsOrder,paidOrNot;
       // int id;
        Rezervation rezervation;

        public ItemForRezervationsList(Rezervation ld){
            /*time = ld.gettime();
            name_user = ld.getname_user();
            numberTable = ld. getnumberTable();
            price = ld.getprice();
            itemsOrder = ld.getitemsOrder();
            paidOrNot = ld.getpaidOrNot();
            id = ld.getId();*/
            rezervation = ld;

        }
        @Override
        public boolean isEnabled() {//cemu sluzi
            return true;
        }

        @Override
        public View getView(Context context, View convertView, ViewGroup parent) {
            return super.getView(context, convertView, parent);
        }

        @Override
        protected int getViewLayoutResId() {
            return R.layout.rezervation;
        }

        @Override
        protected  IViewHolder createViewHolder() {
            return  new RezervationsViewHolder();
        }

        private class  RezervationsViewHolder implements IViewHolder<ItemForRezervationsList> {
            TextView time, name_user, numberTable, price, itemsOrder, paidOrNot;
            Button edit, remove;

            @Override
            public void findViews(View convertView) {
                time = (TextView)convertView.findViewById(R.id.time);
                name_user= (TextView)convertView.findViewById(R.id.name_user);
                numberTable= (TextView)convertView.findViewById(R.id.numberTable);
                price= (TextView)convertView.findViewById(R.id.price);
                itemsOrder = (TextView)convertView.findViewById(R.id.itemsOrder);
                paidOrNot = (TextView)convertView.findViewById(R.id.paidOrNot);
                edit  = (Button)convertView.findViewById(R.id.edit);
                remove = (Button)convertView.findViewById(R.id.remove);
            }
            @Override
            public void fillData(final ItemForRezervationsList adapterItem) {

                time.setVisibility(View.VISIBLE);
                time.setText(adapterItem.rezervation.gettime());
                name_user.setVisibility(View.VISIBLE);
                name_user.setText(adapterItem.rezervation.getname_user());
                numberTable.setVisibility(View.VISIBLE);
                numberTable.setText(adapterItem.rezervation.getnumberTable_string());
                price.setVisibility(View.VISIBLE);
                price.setText(adapterItem.rezervation.getprice());
                itemsOrder.setVisibility(View.VISIBLE);
                itemsOrder.setText(adapterItem.rezervation.getItemsOrdersInString());
                paidOrNot.setVisibility(View.VISIBLE);
                paidOrNot.setText(adapterItem.rezervation.getnumberTable_string());


                edit.setVisibility(View.VISIBLE);
                remove.setVisibility(View.VISIBLE);

                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //otvori prozor fragment FreagmentAddOrder
                        Intent intent2 = new Intent(getActivity().getApplicationContext(), ActivityFirst.class);
                        intent2.putExtra("name", "FreagmentAddOrder");
                        intent2.putExtra("rezervationId", Integer.toString(rezervation.getId()));

                        intent2.putExtra("action", "onclick");
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().getApplicationContext().startActivity(intent2);
                    }
                });

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AdapterDB.getInstance().deleteRezervation(rezervation.getId());
                        ((MyCustomAdatperForTheList<ItemForRezervationsList>) lvDetail.getAdapter()).deleteItemFromAdapter(ItemForRezervationsList.this);

                    }
                });
            }
        }
    }



}

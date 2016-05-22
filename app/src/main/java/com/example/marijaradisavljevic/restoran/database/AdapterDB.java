package com.example.marijaradisavljevic.restoran.database;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

/**
 * Created by marija.radisavljevic on 5/20/2016.
 */
public class AdapterDB {

    private static AdapterDB instance = new AdapterDB();
    public static AdapterDB getInstance() {return instance; }

    private Semaphore listFoodMenuItem_semaphore = new Semaphore(1);
    private Semaphore listOfRezervations_semaphore = new Semaphore(1);

    private ArrayList<FoodMenuItem> listFoodMenuItem;
    //i get list of reservatins from back end
    private ArrayList<Rezervation> listOfRezervations;

    public AdapterDB() {
        try {


            listFoodMenuItem = new ArrayList<FoodMenuItem>();

            FoodMenuItem fmt1 = new FoodMenuItem("koka kola", 100);
            listFoodMenuItem.add(fmt1);
            FoodMenuItem fmt2 = new FoodMenuItem("koka kola", 100);
            listFoodMenuItem.add(fmt2);

            FoodMenuItem fmt3 = new FoodMenuItem("turska kafa", 100);
            listFoodMenuItem.add(fmt3);

            FoodMenuItem fmt4 = new FoodMenuItem("espreso", 100);
            listFoodMenuItem.add(fmt4);






            listOfRezervations = new ArrayList<Rezervation>();


            Rezervation ld = new Rezervation();

            ld.setName_user("milica jelic");
            // ld.setItemsOrder(new ArrayList(Arrays.asList("kapucino , truska kafa, lenja pita sa jabukama")));
            ArrayList<Order> listOrders = new ArrayList<Order>();
            listOrders.add(new Order(1, fmt1, 1));
            listOrders.add(new Order(3, fmt2, 1));
            listOrders.add(new Order(4, fmt3, 1));
            ld.setOrders(listOrders);
            ld.setNumberTable(3);
            ld.setPaidOrNot(true);

            ld.setTime("5.5.2016. 17:30 ");
            listOfRezervations.add(ld);

            ld = new Rezervation();

            ld.setName_user("Ana Ilic");
            //ld.setItemsOrder(new ArrayList(Arrays.asList("kapucino , truska kafa, lenja pita sa jabukama")));
            listOrders = new ArrayList<Order>();
            listOrders.add(new Order(1, fmt1, 9));
            listOrders.add(new Order(3, fmt2, 9));
            listOrders.add(new Order(4, fmt3, 9));
            ld.setOrders(listOrders);
            ld.setNumberTable(2);
            ld.setPaidOrNot(true);

            ld.setTime("5.5.2016. 18:30 ");
            listOfRezervations.add(ld);

            ld = new Rezervation();

            ld.setName_user("milanka rajicic");
            // ld.setItemsOrder(new ArrayList(Arrays.asList("koka kola , koka kola, lenja pita sa jabukama")));
            listOrders = new ArrayList<Order>();
            listOrders.add(new Order(1, fmt1, 71));
            listOrders.add(new Order(3, fmt2, 17));
            listOrders.add(new Order(4, fmt3, 17));
            ld.setOrders(listOrders);
            ld.setNumberTable(2);
            ld.setPaidOrNot(true);
            ld.setTime("5.5.2016. 18:00");
            listOfRezervations.add(ld);


            ld = new Rezervation();
            ld.setName_user("novak stojanovic");
            //  ld.setItemsOrder(new ArrayList(Arrays.asList("jelen pivo ,crveno vino , lenja pita sa jabukama")));
            listOrders = new ArrayList<Order>();
            listOrders.add(new Order(1, fmt1, 12));
            listOrders.add(new Order(3, fmt2, 13));
            listOrders.add(new Order(4, fmt3, 13));
            ld.setOrders(listOrders);
            ld.setNumberTable(1);

            ld.setPaidOrNot(true);

            ld.setTime("5.5.2016. 17:00 ");
            listOfRezervations.add(ld);





    }



    //TODO 5/19/2016  this  is read from DB
    public ArrayList<Rezervation> getRezervations() {
        return  listOfRezervations;
    }

    public void updateRezervations (int id){


    }

    public void  deleteRezervation(int id){
        try{
        listOfRezervations_semaphore.acquire();
        Iterator<Rezervation> iter = listOfRezervations.iterator();
        while (iter.hasNext()){
            Rezervation tek;
            tek = iter.next();
            if(tek.getId() == id){
               listOfRezervations.remove(tek);

            }
        }


        }catch (Exception e){

        }finally {
            listOfRezervations_semaphore.release();
        }

    }



    public Rezervation getClickActionRezervation(int id){
        try{
        listOfRezervations_semaphore.acquire();

Rezervation retrunValue = null;
        Iterator<Rezervation> iter = listOfRezervations.iterator();
        while (iter.hasNext()){
            Rezervation tek;
            tek = iter.next();
            if(tek.getId() == id){
                retrunValue =  tek;

            }
        }

        return  retrunValue;//todo  mora da postoji taj id , nikad ne vrati null

        }catch (Exception e){

            return null;
        }finally {
            listOfRezervations_semaphore.release();
        }
    }

    public Rezervation getNewRezervation() {
        try {
            listOfRezervations_semaphore.acquire();
            Rezervation r = new Rezervation();
            listOfRezervations.add(r);

            return r;
             }catch (Exception e){

return null;
            }finally {
            listOfRezervations_semaphore.release();
        }
    }
    public void  putRezervation(Rezervation r) {
        Rezervation rez = getClickActionRezervation(r.getId());

        rez = r;

    }
}

package cr.ac.itcr.shopadvisor.api;

import java.util.ArrayList;

import cr.ac.itcr.shopadvisor.entity.Place;

/**
 * Created by car_e on 5/4/2016.
 */
public interface IApi<Object> {
    public boolean Save (Object object);

    public boolean Update (Object object);

    public boolean Delete (Object object);

    public ArrayList<Place> GetAll ();

    public ArrayList<Place> GetBy (Object object);


}

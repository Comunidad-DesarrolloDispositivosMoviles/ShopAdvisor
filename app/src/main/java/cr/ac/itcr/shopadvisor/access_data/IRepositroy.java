package cr.ac.itcr.shopadvisor.access_data;

import java.util.ArrayList;

/**
 * Created by Mendez Soto on 3/30/2016.
 */
public interface IRepositroy<Object> {
    public boolean Save(Object object);

    public boolean Update(Object object);

    public boolean Delete(Object object);

    public ArrayList<Object> GetAll();

    public ArrayList<Object> GetBy(Object object);
}

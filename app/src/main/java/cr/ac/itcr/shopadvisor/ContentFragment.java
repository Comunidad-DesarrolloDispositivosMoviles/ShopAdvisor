package cr.ac.itcr.shopadvisor;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtContent;

    private OnFragmentInteractionListener mListener;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content,container,false);
        txtContent = (TextView)v.findViewById(R.id.txtContacts);
        this.obtenerLlamadas();
        // Inflate the layout for this fragment
        return v;
    }


    public void obtenerLlamadas(){
        Uri uri;
        uri = Uri.parse("content://call_log/calls");

        String [] projeccion = new String[]{
                CallLog.Calls.TYPE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION
        };

        Cursor llamadas = getActivity().getContentResolver().query(
                uri,
                projeccion,
                null,
                null,
                null
        );
        while(llamadas.moveToNext()){
            txtContent.append("Tipo: " + llamadas.getString(0) +
                    " Numero: " + llamadas.getString(1) +
                    " Duracion: " + llamadas.getString(2)+"\n");
        }
    }


    public void obtenerDatos(){
        String[] projeccion = new String[]{
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE};
        String selectionClause = ContactsContract.Data.MIMETYPE + " = '"+ ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                +"' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER+" IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor contactos = getActivity().getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,//What to get
                selectionClause,//Where
                null,
                sortOrder);//Order
        //Tira nombre y numero igual
        //Es por el formato de los contactos, lo devuelve differetne
        while(contactos.moveToNext()){
            txtContent.append("Identificador: " + contactos.getString(0) +
                    " Nombre: " + contactos.getString(contactos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) +
                    " Numero: " + contactos.getString(2) +
                    " Tipo: " + contactos.getString(3) + "\n");
            Cursor cur1 = getActivity().getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                    new String[]{contactos.getString(0)}, null);
            while (cur1.moveToNext()) {
                txtContent.append("CURSOR NAME BITCH: " + cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            }
            cur1.close();
        }
        contactos.close();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

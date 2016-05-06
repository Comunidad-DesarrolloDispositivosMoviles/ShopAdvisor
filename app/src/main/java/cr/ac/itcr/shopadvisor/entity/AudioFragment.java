package cr.ac.itcr.shopadvisor.entity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import cr.ac.itcr.shopadvisor.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AudioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioFragment extends Fragment implements MediaPlayer.OnPreparedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //Variables to set the audio component
    private MediaPlayer mp;
    private int posicion = 0;

    private OnFragmentInteractionListener mListener;

    public AudioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudioFragment newInstance(String param1, String param2) {
        AudioFragment fragment = new AudioFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio,container,false);
        final Button btnAudio = (Button)view.findViewById(R.id.btnAudio);
        final Button btnAudioInit = (Button)view.findViewById(R.id.btnInitiate);
        final Button btnAudioPause = (Button)view.findViewById(R.id.btnPause);
        final Button btnAudioContinue = (Button)view.findViewById(R.id.btnContinue);
        final Button btnAudioStop = (Button)view.findViewById(R.id.btnStop);
        final Button btnAudioCirculate = (Button)view.findViewById(R.id.btnCirculate);
        final Button btnAudioInternet = (Button)view.findViewById(R.id.btnInternet);

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.flex);
                mp.start();
            }
        });

        btnAudioInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.flex);
                mp.start();
                String cp = btnAudioCirculate.getText().toString();
                if(cp.equals("no reproducir en forma circular"))
                    mp.setLooping(false);
                else
                    mp.setLooping(true);
            }
        });

        btnAudioPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mp!=null || mp.isPlaying()){
                   posicion = mp.getCurrentPosition();
                   mp.pause();
               }
            }
        });

        btnAudioContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null || mp.isPlaying() == false){
                    mp.seekTo(posicion);
                    mp.start();
                }
            }
        });

        btnAudioStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp!=null){
                    mp.stop();
                    posicion = 0;
                }
            }
        });

        btnAudioCirculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String op = btnAudioCirculate.getText().toString();
                if(op.equals("no reproducir en forma circular"))
                    btnAudioCirculate.setText("reproducir en forma circular");
                else
                    btnAudioCirculate.setText("no reproducir en forma circular");

            }
        });

        btnAudioInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(mp!=null){
                    mp.stop();
                    posicion = 0;
                }

                MediaPlayer mp = new MediaPlayer();
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });

                try{
                    mp.setDataSource("http://www.javaya.com.ar/recursos/gato.mp3");
                    mp.prepareAsync();
                }catch(IOException e){
                    Toast t = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    t.show();
                }
                Toast t = Toast.makeText(getActivity().getApplicationContext(), "Espere mientras carga el mp3", Toast.LENGTH_LONG);
                t.show();
            }
        });


        return view;


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

    @Override
    public void onPrepared(MediaPlayer mp) {

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

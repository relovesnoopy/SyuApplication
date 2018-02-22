package jp.ac.hal.syuapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String strbuy = "売却";
    private String strask = "購入";
    private double max;
    private double min;
    private String Maxexchange;
    private String Minexchange;

    private OnFragmentInteractionListener mListener;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
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
        //初期化
        max = 0;
        min = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv1 = view.findViewById(R.id.textView1);
        tv2 = view.findViewById(R.id.textView2);
        tv3 = view.findViewById(R.id.textView3);
        tv4 = view.findViewById(R.id.textView4);
        tv5 = view.findViewById(R.id.textView5);
        tv6 = view.findViewById(R.id.textView6);
        tv7 = view.findViewById(R.id.textView7);
        tv8 = view.findViewById(R.id.textView8);
        tv9 = view.findViewById(R.id.textView9);
        tv10 = view.findViewById(R.id.textView10);
        tv11 = view.findViewById(R.id.textView11);
        tv12 = view.findViewById(R.id.textView12);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                int result = (int)(max - min);//差額
                Toast.makeText(getActivity(), Minexchange + "で購入して" + Maxexchange + "で売却すると" + String.format("%1$,3d円", result) + "の利益" ,Toast.LENGTH_LONG).show();
                Log.v("max", Maxexchange);
                //Log.v("min", Minexchange);
                onResume();

            }
        });
        AsyncJson();
    }

    private void AsyncJson() {
        JsonLoader jsonLoader = new JsonLoader(new JsonLoader.AsyncCallback() {
            @Override
            public void preExecute() {
            }

            @Override
            public void postExecute(List<String> result) {
                JSONObject json;
                try {
                    int i = 0;
                    for (String list : result){
                        json = new JSONObject(list);
                        double buy = 0;//売却値
                        double ask = 0;//購入値
                        String exchange = "";//取引所
                        switch (i){
                            case 0:
                                buy = Double.parseDouble(json.getString("bid"));
                                ask = Double.parseDouble(json.getString("ask"));
                                exchange = "Coincheck";
                                //Coincheck
                                tv1.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv2.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            case 1:
                                buy = Double.parseDouble(json.getString("best_bid"));
                                ask = Double.parseDouble(json.getString("best_ask"));
                                exchange = "bitFlyer";
                                //bitFlyer
                                tv3.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv4.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            case 2:
                                buy = Double.parseDouble(json.getString("bid"));
                                ask = Double.parseDouble(json.getString("ask"));
                                exchange = "Zaif";
                                //Zaif
                                tv5.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv6.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            case 3:
                                buy = Double.parseDouble(json.getString("buy"));
                                ask = Double.parseDouble(json.getString("sell"));
                                exchange = "BtcBox";
                                //BtcBox
                                tv7.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv8.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            case 4:
                                buy = Double.parseDouble(json.getString("market_bid"));
                                ask = Double.parseDouble(json.getString("market_ask"));
                                exchange = "Quoinex";
                                //Quoinex
                                tv9.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv10.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            case 5:
                                json = new JSONObject(json.getString("data"));
                                buy = Double.parseDouble(json.getString("buy"));
                                ask = Double.parseDouble(json.getString("sell"));
                                exchange = "bitbank";
                                //bitbank
                                tv11.setText(strbuy + String.format("%1$,3d円", (int)buy));
                                tv12.setText(strask + String.format("%1$,3d円", (int)ask));
                                break;
                            default:
                                break;
                        }
                        CheckCoin(buy, ask, exchange);
                        i++;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void progressUpdate(int progress) {

            }

            @Override
            public void cancel() {

            }
        });
        //各取引所API
        String[] strings = {"https://coincheck.com/api/ticker", "https://api.bitflyer.jp/v1/ticker", "https://api.zaif.jp/api/1/ticker/btc_jpy", "https://www.btcbox.co.jp/api/v1/ticker/", "https://api.quoine.com/products/5", "https://public.bitbank.cc/btc_jpy/ticker"};
        jsonLoader.execute(strings);
    }

    private void CheckCoin(double buy, double ask, String exchange) {
        if(max == 0.0 && min == 0.0){
            max = buy;
            min = ask;
            Maxexchange = exchange;
            Minexchange = exchange;
        }
        if (max < buy){
            this.max = buy;
            this.Maxexchange = exchange;
        }
        if(ask < min){
            this.min = ask;
            this.Minexchange = exchange;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

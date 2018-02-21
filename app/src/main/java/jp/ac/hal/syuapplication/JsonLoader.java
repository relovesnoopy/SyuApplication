package jp.ac.hal.syuapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by muto.masakazu on 2018/02/15.
 */

public class JsonLoader extends AsyncTask<String, Integer, List<String>> {
    public interface AsyncCallback {
        void preExecute();
        void postExecute(List<String> result);
        void progressUpdate(int progress);
        void cancel();
    }
    private AsyncCallback mCallbask = null;

    public JsonLoader(AsyncCallback callbask) {
        this.mCallbask = callbask;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCallbask.preExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... _progress) {
        super.onProgressUpdate(_progress);
        mCallbask.progressUpdate(_progress[0]);
    }

    @Override
    protected void onPostExecute(List<String> _result) {
        super.onPostExecute(_result);
        mCallbask.postExecute(_result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mCallbask.cancel();
    }

    @Override
    protected List<String> doInBackground(String... address) {
        return JsonGet(address);
    }

    /*
    * httpGet通信処理
    * */
    private List<String> JsonGet(String[] urls){

        HttpURLConnection urlCon;
        InputStream in;
        List<String> result;
        try {
            result = new ArrayList<>();
            for(String url:urls) {
                //urlCon = (HttpURLConnection) new URL(urls[0]).openConnection();
                urlCon = (HttpURLConnection) new URL(url).openConnection();
                urlCon.setRequestMethod("GET");
                urlCon.setDoInput(true);
                urlCon.connect();

                String str_json;
                in = urlCon.getInputStream();
                InputStreamReader objReader = new InputStreamReader(in);
                BufferedReader objBuf = new BufferedReader(objReader);
                StringBuilder strBuilder = new StringBuilder();
                String sLine;
                while ((sLine = objBuf.readLine()) != null) {
                    strBuilder.append(sLine);
                }
                str_json = strBuilder.toString();
                in.close();
                //リストに追加
                result.add(str_json);
            }
            return result;

            //return str_json;

        } catch (IOException e) {
            e.printStackTrace();
            //return "network_error";
        }
        return null;
    }
}

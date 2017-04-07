package test.bwie.com.xm01zhoukaoone;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/4/7
 */

public class MyAsyncTask extends AsyncTask<String,Integer,List<GsonBean.ListBean>>{
    private ListView lv;
    private Context context;
    private MyAdapter mAdapter;

    public MyAsyncTask(ListView lv, Context context) {
        this.lv = lv;
        this.context = context;
    }

    @Override
    protected void onPostExecute(final List<GsonBean.ListBean> list) {
        super.onPostExecute(list);
        if(list.size()!=0){
            mAdapter = new MyAdapter(list,context);
            lv.setAdapter(mAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context,list.get(position).getId()+"",Toast.LENGTH_SHORT).show();
                }
            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    list.remove(position);
                    mAdapter.notifyDataSetChanged();
                    return false;
                }
            });
        }
    }

    @Override
    protected List<GsonBean.ListBean> doInBackground(String [] params) {
        try {
            StringBuffer sb=new StringBuffer();
            URL url=new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            if(connection.getResponseCode()==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                String line=null;
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                if(sb!=null){
                    Gson gson=new Gson();
                    GsonBean gsonBean = gson.fromJson(sb.toString(), GsonBean.class);
                    if(gsonBean!=null){
                        List<GsonBean.ListBean> list = gsonBean.getList();
                        if(list.size()!=0){
                            return list;
                        }
                    }
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

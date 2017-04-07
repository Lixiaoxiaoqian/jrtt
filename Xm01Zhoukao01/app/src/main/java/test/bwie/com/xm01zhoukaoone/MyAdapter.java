package test.bwie.com.xm01zhoukaoone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * @类的用途：
 * @author: 李晓倩
 * @date: 2017/4/7
 */

public class MyAdapter extends BaseAdapter{
    private List<GsonBean.ListBean> mList;
    private Context mContext;

    public MyAdapter(List<GsonBean.ListBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.lv_item,null);
            holder=new ViewHolder();
            holder.tv1= (TextView) convertView.findViewById(R.id.textView3);
            holder.tv2= (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tv1.setText(mList.get(position).getSite_name());
        holder.tv2.setText(mList.get(position).getAddress());
        return convertView;
    }

    class ViewHolder{
        TextView tv1;
        TextView tv2;
    }
}

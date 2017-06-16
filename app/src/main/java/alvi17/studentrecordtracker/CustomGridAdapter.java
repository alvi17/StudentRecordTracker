package alvi17.studentrecordtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by User on 5/12/2017.
 */

public class CustomGridAdapter extends BaseAdapter{

    Context context;
    ArrayList<Batch> batchInfo;
    LayoutInflater layoutinflater;

    public CustomGridAdapter(Context context,ArrayList<Batch> infos)
    {
        this.context=context;
        this.batchInfo=infos;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return batchInfo.size();
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
        ViewHolder listViewHolder;

        if(convertView == null){
            //initialize here
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.batch_grid_item, parent, false);
            listViewHolder.batch_button = (Button) convertView.findViewById(R.id.batch_button);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.batch_button.setText(batchInfo.get(position).getName()+" : "+batchInfo.get(position).getYear());

        if(position%4==0)
        {
            listViewHolder.batch_button.setBackgroundResource(R.drawable.batch_buttons_back);
        }
        else if(position%4==1)
        {
            listViewHolder.batch_button.setBackgroundResource(R.drawable.batch_button_back1);
        }
        else if(position%4==2)
        {
            listViewHolder.batch_button.setBackgroundResource(R.drawable.batch_button_back2);
        }
        else if(position%4==3)
        {
            listViewHolder.batch_button.setBackgroundResource(R.drawable.batch_button_back3);
        }


        //set text or images here
        return convertView;
    }


    static class ViewHolder{
        Button batch_button;
    }

}

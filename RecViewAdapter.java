package samplerecv.aebhi.addictech.samplerecyclerview.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import samplerecv.aebhi.addictech.samplerecyclerview.Models.RecViewData;
import samplerecv.aebhi.addictech.samplerecyclerview.R;

/**
 * To create a recyclerview , we need 2 classes, Adapter to bind data with view... and viewholder to present the view
 */

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolderRView> {


    class ViewHolderRView extends RecyclerView.ViewHolder{
        ImageView img;
        TextView text1,text2;

        public ViewHolderRView(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image1);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            text2 = (TextView) itemView.findViewById(R.id.text2);


        }
    }

    private Context mCtx;
    private ArrayList<RecViewData> datalist;

    public RecViewAdapter(Context mCtx, ArrayList<RecViewData> datalist) {
        this.mCtx = mCtx;
        this.datalist = datalist;
    }

    @Override
    public ViewHolderRView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.screen1_rec_view_row, null);
        ViewHolderRView holder =  new ViewHolderRView(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderRView holder, int position) {
        RecViewData viewData = datalist.get(position);
        holder.text1.setText(viewData.getData1());
        holder.text2.setText(String.valueOf(viewData.getData2()));
        Glide.with(mCtx).load(viewData.getData3()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

}

package com.praire.fire.map.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.praire.fire.R;
import com.praire.fire.data.IntentDataForRoutePlanningActivity;
import com.praire.fire.map.RoutePlanningActivity;
import com.praire.fire.map.bean.NearlyShopBean;
import com.praire.fire.order.adapter.OrderListAdapter;
import com.praire.fire.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Intent.ACTION_CALL;


public class NearlyShopAdapter extends RecyclerView.Adapter<NearlyShopAdapter.MyViewHolder> implements View.OnClickListener{


    private Context context;
    private List<NearlyShopBean> entities = new ArrayList<>();
    private AMapLocation location;
    private LatLng startlatLng;
    private String distance = "0km";
    private double latitude;
    private double longitude;

    public NearlyShopAdapter(Context context) {
        this.context = context;

    }

    public void setEntities(List<NearlyShopBean> entities, double longitude, double latitude) {
        this.entities = entities;
        this.longitude = longitude;
        this.latitude = latitude;
        startlatLng = new LatLng(latitude, longitude);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                context).inflate(R.layout.item_map_nearly_shop, parent,
                false);
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        final NearlyShopBean item = entities.get(position);
        holder.itemMapNearlyName.setText(item.getName());
        holder.itemMapNearlyStar.setRating(Float.valueOf(item.getStar()));
        holder.itemMapNearlyTrade.setText(item.getType_name());
        holder.itemMapNearlyPerson.setText(item.getOrdercount() + "人去过");
        TextViewUtils.changeFontColor(context, holder.itemMapNearlyPerson, 0, item.getOrdercount().length(), R.color.grey, R.color.orange);
        holder.itemMapNearlyAddress.setText(item.getAddress());


        holder.itemMapNearlyDistance.setText(item.getDistance() + "km");
        holder.itemMapNearlyTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(new Intent(ACTION_CALL, Uri.parse("tel:" + item.getTel())));
            }
        });
        holder.itemMapNearlyRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentDataForRoutePlanningActivity data = new IntentDataForRoutePlanningActivity();
                data.mStartPoint = new LatLonPoint(latitude, longitude);
                data.mEndPoint = new LatLonPoint(Double.valueOf(item.getLat()), Double.valueOf(item.getLng()));
                RoutePlanningActivity.startActivity(context, data, false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    @Override
    public void onClick(View view) {
        if(itemClickLister !=null){
            itemClickLister.oncheckd((int)view.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_map_nearly_name)
        TextView itemMapNearlyName;
        @BindView(R.id.item_map_nearly_star)
        RatingBar itemMapNearlyStar;
        @BindView(R.id.item_map_nearly_trade)
        TextView itemMapNearlyTrade;
        @BindView(R.id.item_map_nearly_person)
        TextView itemMapNearlyPerson;
        @BindView(R.id.item_map_nearly_distance)
        TextView itemMapNearlyDistance;
        @BindView(R.id.item_map_nearly_address)
        TextView itemMapNearlyAddress;
        @BindView(R.id.item_map_nearly_tel)
        TextView itemMapNearlyTel;
        @BindView(R.id.item_map_nearly_round)
        TextView itemMapNearlyRound;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
    ItemClickLister itemClickLister;

    public void setDataBack(ItemClickLister itemClickLister) {
        this.itemClickLister = itemClickLister;
    }

    public interface ItemClickLister {
        void oncheckd(int position);
    }

}


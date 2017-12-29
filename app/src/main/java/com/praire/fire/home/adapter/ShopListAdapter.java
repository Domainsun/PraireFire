package com.praire.fire.home.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.praire.fire.R;
import com.praire.fire.home.bean.ShopBean;

import java.util.List;

/**
 * 评价列表适配器
 */

public class ShopListAdapter extends BaseQuickAdapter<ShopBean.PagelistBean, BaseViewHolder> {


    public ShopListAdapter(int layoutResId, @Nullable List<ShopBean.PagelistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBean.PagelistBean item) {
        helper.setText(R.id.item_shop_list_name, item.getName());
        helper.setRating(R.id.item_shop_list_star,Float.valueOf(item.getStar()));
        helper.setText(R.id.item_shop_list_scroe, item.getStar()+"分")
                .setText(R.id.item_shop_list_time, item.getOpentime())
                .setText(R.id.item_shop_list_info, item.getDesc())
                .setText(R.id.item_shop_list_address, item.getAddress());

        helper.setOnClickListener(R.id.item_shop_list_address, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//                .setText(R.id.item_shop_list_distance, item.get());

//                .setVisible(R.id.tweetRT, item.isRetweet())
//                .addOnClickListener(R.id.tweetAvatar)
//                .addOnClickListener(R.id.tweetName)
//                .linkify(R.id.tweetText);

//        helper.setImageResource(R.id.icon, item.getDoor());
        // 加载网络图片

       Uri uri =  Uri.parse(item.getDoor());
        SimpleDraweeView    sdv = (SimpleDraweeView) helper.getView(R.id.item_shop_list_img);//.setImageUrl(R.id.item_shop_list_img,item.getDoor());
       DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        sdv.setController(controller);
    }


}


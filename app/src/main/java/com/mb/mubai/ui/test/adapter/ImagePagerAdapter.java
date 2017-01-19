package com.mb.mubai.ui.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.mb.mubai.R;
import com.mb.mubai.ui.test.activity.SeekBarActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 *
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/25
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 *
 */

public class ImagePagerAdapter extends BaseAdapter {
        private Context context;
        private List<String> imageIdList;
        private List<String> linkUrlArray;
        private List<String> urlTitlesList;
        private int size;
        private boolean isInfiniteLoop;
        private ImageLoader imageLoader;
        private DisplayImageOptions options;

        public ImagePagerAdapter(Context context, List<String> imageIdList,
                                 List<String> urllist, List<String> urlTitlesList) {
                this.context = context;
                this.imageIdList = imageIdList;
                if (imageIdList != null) {
                        this.size = imageIdList.size();
                }
                this.linkUrlArray = urllist;
                this.urlTitlesList = urlTitlesList;
                isInfiniteLoop = false;
                // 初始化imageLoader 否则会报错
                imageLoader = ImageLoader.getInstance();
                imageLoader.init(ImageLoaderConfiguration.createDefault(context));
                options = new DisplayImageOptions.Builder()
                          .showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                          .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                          .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                          .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                          .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                          .build();

        }

        @Override
        public int getCount() {
                // Infinite loop
                return isInfiniteLoop ? Integer.MAX_VALUE : imageIdList.size();
        }

        /**
         * get really position
         *
         * @param position
         * @return
         */
        private int getPosition(int position) {
                return isInfiniteLoop ? position % size : position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup container) {
                final ViewHolder holder;
                if (view == null) {
                        holder = new ViewHolder();
                        view = holder.imageView = new ImageView(context);
                        holder.imageView
                                  .setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        view.setTag(holder);
                } else {
                        holder = (ViewHolder) view.getTag();
                }

                imageLoader.displayImage(
                          (String) this.imageIdList.get(getPosition(position)),
                          holder.imageView, options);

                holder.imageView.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                                String url = linkUrlArray.get(ImagePagerAdapter.this
                                          .getPosition(position));
                                String title = urlTitlesList.get(ImagePagerAdapter.this
                                          .getPosition(position));
                /*
                 * if (TextUtils.isEmpty(url)) {
                 * holder.imageView.setEnabled(false); return; }
                 */
                                Bundle bundle = new Bundle();

                                bundle.putString("url", url);
                                bundle.putString("title", title);
                                Intent intent = new Intent(context, SeekBarActivity.class);
                                intent.putExtras(bundle);

                                context.startActivity(intent);
                                Toast.makeText(context, "点击了第" + getPosition(position) + "美女",
                                          Toast.LENGTH_SHORT).show();

                        }
                });

                return view;
        }

        private static class ViewHolder {

                ImageView imageView;
        }

        /**
         * @return the isInfiniteLoop
         */
        public boolean isInfiniteLoop() {
                return isInfiniteLoop;
        }

        /**
         * @param isInfiniteLoop
         *            the isInfiniteLoop to set
         */
        public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
                this.isInfiniteLoop = isInfiniteLoop;
                return this;
        }

        @Override
        public Object getItem(int arg0) {
                // TODO Auto-generated method stub
                return arg0;
        }

        @Override
        public long getItemId(int arg0) {
                // TODO Auto-generated method stub
                return arg0;
        }
}

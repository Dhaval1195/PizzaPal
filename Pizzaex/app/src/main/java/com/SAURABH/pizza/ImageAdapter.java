package com.SAURABH.pizza;

import com.SAURABH.pizza.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	int mGalleryItemBackground;
    private Context mContext;

    private Integer[] mImageIds = {
            R.drawable.anchovies,
            R.drawable.bacon,
            R.drawable.bananapepper,
            R.drawable.blackolives,
            R.drawable.chicken,
            R.drawable.greenpeppers,
            R.drawable.ham,
            R.drawable.jalapenopeppers,
            R.drawable.mozzarella,
            R.drawable.mushrooms,
            R.drawable.onion,
            R.drawable.pepperoni,
            R.drawable.pineapple,
            R.drawable.sausage,
            R.drawable.tomatoes,
    };

    public ImageAdapter(Context c) {
        mContext = c;
        TypedArray a = c.obtainStyledAttributes(R.styleable.HelloGallery);
        mGalleryItemBackground = a.getResourceId(
                R.styleable.HelloGallery_android_galleryItemBackground, 0);
        a.recycle();
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[position]);
        i.setLayoutParams(new Gallery.LayoutParams(150, 100));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        i.setBackgroundResource(mGalleryItemBackground);

        return i;
    }
}

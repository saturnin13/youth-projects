package uk.co.jumprapp.photos;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyPhotoRecyclerViewAdapter extends RecyclerView.Adapter<MyPhotoRecyclerViewAdapter.PhotoViewHolder> {

    private static final int PHOTO_VIEW_HOLDER = 0;

    private final List<Photo> photos;
    private final PhotoListFragment.OnPhotoListFragmentInteractionListener mListener;

    public MyPhotoRecyclerViewAdapter(List<Photo> items, PhotoListFragment.OnPhotoListFragmentInteractionListener listener) {
        photos = items;
        mListener = listener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v1 = layoutInflater.inflate(R.layout.photo_list_item, parent, false);
        viewHolder = new PhotoViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (holder.getItemViewType() == PHOTO_VIEW_HOLDER) {
            PhotoViewHolder basketItemViewHolder = holder;
            configurePhotoViewHolder(basketItemViewHolder, position);
        }

     /*   Glide
        .with((Activity) mListener)
        .load(photo.getFile())
        .asBitmap()
        .into(new SimpleTarget<Bitmap>(100, 100) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                //image.setImageBitmap(resource); // Possibly runOnUiThread()
                double quality = QualityMeasure.getQuality(resource);
                holder.textView.setText("" + quality);
            }
        }); */
    }


    private void configurePhotoViewHolder(final PhotoViewHolder holder, final int position) {
        holder.mItem = photos.get(position);

        if (!holder.mItem.isGoodPhoto()) {
            holder.container.setAlpha(0.2f);
        } else {
            holder.container.setAlpha(1f);
        }

        if (holder.mItem.isSelected()) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }

        ViewGroup.LayoutParams imageParams = holder.imageView.getLayoutParams();

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    if (mListener.isSelectMode()) {
                        if (holder.mItem.isSelected()) {
                            holder.mItem.setSelected(false);
                            mListener.removeFromSelected(holder.mItem);
                            mListener.decCount();
                        } else {
                            holder.mItem.setSelected(true);
                            mListener.addToSelected(holder.mItem);
                            mListener.succCount();
                        }
                        notifyItemChanged(position);
                        if (mListener.numberSelected() == 0) {
                            mListener.setSelectMode(false);
                        }
                    } else {
                        mListener.onPhotoListSingleClick(holder.mItem);
                    }
                }
            }
        });

        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!mListener.isSelectMode()) {
                    mListener.setSelectMode(true);
                }
                holder.mItem.setSelected(true);
                mListener.addToSelected(holder.mItem);
                mListener.succCount();
                mListener.decCount();
                notifyItemChanged(position);
                return true;
            }
        });
        Glide.with((Activity) mListener)
                .load(holder.mItem.getFile())
                .centerCrop()
                .crossFade()
                .into(holder.imageView);
        holder.imageView.setLayoutParams(imageParams);
    }
/*
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return IMAGE_VIEW_HOLDER;
        }
    }  */

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public View container;
        public View innerContainer;
        public ImageView imageView;
        public ImageView checkBox;
        public Photo mItem;

        public TextView textView;

        public PhotoViewHolder(View view) {
            super(view);
            container = view;
            checkBox = (ImageView) view.findViewById(R.id.checkbox);
            innerContainer = view.findViewById(R.id.inner_photo_container);
            ViewGroup.LayoutParams params = container.getLayoutParams();
            params.height = mListener.getWidth() / 3;
            params.width = mListener.getWidth() / 3;
            container.setLayoutParams(params);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + container.toString() + "'";
        }
    }
}

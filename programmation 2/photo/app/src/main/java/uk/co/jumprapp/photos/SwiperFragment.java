package uk.co.jumprapp.photos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class SwiperFragment extends Fragment {

    private static final String PHOTO_LIST = "photo-list";
    private static int numPage = 1;
    private OnSwiperFragmentInteractionListener mListener;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Button mPositiveButton;
    private Button mNegativeButton;

    private List<Photo> photoList;

    public SwiperFragment() {
        // Required empty public constructor
    }

    public static SwiperFragment newInstance(List<Photo> photoList) {
        SwiperFragment fragment = new SwiperFragment();
        Bundle args = new Bundle();
        args.putSerializable(PHOTO_LIST, (Serializable) photoList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoList = (List<Photo>) getArguments().getSerializable(PHOTO_LIST);
            numPage = photoList.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swiper, container, false);
        mPager = (ViewPager) view.findViewById(R.id.view_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        mPositiveButton = (Button) view.findViewById(R.id.positive_button);
        mNegativeButton = (Button) view.findViewById(R.id.negative_button);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(3);
        return mPager;
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.hideToggle();
        mListener.hideFab();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSwiperFragmentInteractionListener) {
            mListener = (OnSwiperFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSwiperFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSwiperFragmentInteractionListener {
        void onSwiperFragmentInteraction();

        void hideToggle();

        void hideFab();
    }

    public static class ScreenSlidePageFragment extends Fragment {

        private static final String PHOTO = "photo";

        private Photo photo;
        private ImageView imageView;
        private TextView imageDescription;

        private OnScreenSlidePagerAdapterInteractionListener mListener;

        public ScreenSlidePageFragment() {
            // Required empty public constructor
        }


        public static ScreenSlidePageFragment newInstance(Photo photo) {
            ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
            Bundle args = new Bundle();
            args.putSerializable(PHOTO, photo);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                photo = (Photo) getArguments().getSerializable(PHOTO);
            }
        }

        @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                    Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_swiper_photo, container, false);
                imageView = (ImageView) view.findViewById(R.id.swiper_image_view);
                imageDescription = (TextView) view.findViewById(R.id.swiper_image_description);
                Glide.with(this).load(photo.getFile()).into(imageView);
                return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            mListener.hideToggle();
            mListener.hideFab();
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnScreenSlidePagerAdapterInteractionListener) {
                mListener = (OnScreenSlidePagerAdapterInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnScreenSlidePagerAdapterInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }

        public interface OnScreenSlidePagerAdapterInteractionListener {
            void onFragmentInteraction(Uri uri);

            void hideToggle();

            void hideFab();
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Photo photo = photoList.get(position);
            return ScreenSlidePageFragment.newInstance(photo);
        }

        @Override
        public int getCount() {
            return numPage;
        }
    }


}

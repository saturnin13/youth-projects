package uk.co.jumprapp.photos;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnPhotoListFragmentInteractionListener}
 * interface.
 */
public class PhotoListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String PHOTO_LIST = "photo-list";
    private int mColumnCount = 2;
    private OnPhotoListFragmentInteractionListener mListener;

    private List<Pair<Photo, Integer>> badPhotoList;
    private MyPhotoRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Photo> photoList;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotoListFragment() {
    }

    public static PhotoListFragment newInstance(int columnCount) {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        badPhotoList = new ArrayList<>();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            //    photoList = (List<Photo>) getArguments().getSerializable(PHOTO_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photoList = ((MainActivity) getActivity()).getPhotoList();
        adapter = new MyPhotoRecyclerViewAdapter(photoList, mListener);
        recyclerView.setAdapter(adapter);
    }

    public void filterOutBadPhotos() {
        Log.i("Got ere", "BAWDjAIWD");
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < photoList.size(); i++) {
            if (!photoList.get(i).isGoodPhoto()) {
                indexes.add(i);
                badPhotoList.add(new Pair(photoList.get(i), i));
                photoList.remove(i);
                adapter.notifyItemRemoved(i);
                i--;
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        }, 750);
    }

    public void showAllPhotos() {
        for (int i = badPhotoList.size() - 1; i >= 0; i--) {
            Pair<Photo, Integer> badPhoto = badPhotoList.get(i);
            photoList.add(badPhoto.second, badPhoto.first);
            adapter.notifyItemInserted(badPhoto.second);
        }
        badPhotoList.clear();
        for (int i = 0; i < photoList.size(); i++) {
            if (!photoList.get(i).isGoodPhoto()) {
                photoList.get(i).setGoodPhoto(false);
                adapter.notifyItemChanged(i);
            }
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        }, 750);
    }

    @Override
    public void onResume() {
        super.onResume();
        mListener.showToggle();
        mListener.showFab();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPhotoListFragmentInteractionListener) {
            mListener = (OnPhotoListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPhotoListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onPhotoListChanged() {
        adapter.notifyDataSetChanged();
    }

    public interface OnPhotoListFragmentInteractionListener {
        void onPhotoListSingleClick(Photo photo);

        void refreshPhotos();

        int getWidth();

        boolean isSelectMode();

        void setSelectMode(boolean selectMode);

        void succCount();

        int numberSelected();

        void showToggle();

        void showFab();

        void decCount();

        void addToSelected(Photo photo);

        void removeFromSelected(Photo photo);
    }
}

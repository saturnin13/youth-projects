package uk.co.jumprapp.photos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSinglePhotoFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SinglePhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinglePhotoFragment extends Fragment {

    private static final String PHOTO = "photo";

    private Photo photo;
    private ImageView imageView;
    private TextView imageDescription;

    private OnSinglePhotoFragmentInteractionListener mListener;

    public SinglePhotoFragment() {
        // Required empty public constructor
    }


    public static SinglePhotoFragment newInstance(Photo photo) {
        SinglePhotoFragment fragment = new SinglePhotoFragment();
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
        View view = inflater.inflate(R.layout.fragment_single_photo, container, false);
        imageView = (ImageView) view.findViewById(R.id.single_image_view);
        imageDescription = (TextView) view.findViewById(R.id.image_description);
        if (photo.isGoodPhoto()) {
            imageDescription.setText("Good Photo");
        } else {
            imageDescription.setText("Bad Photo");
        }
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
        if (context instanceof OnSinglePhotoFragmentInteractionListener) {
            mListener = (OnSinglePhotoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSinglePhotoFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSinglePhotoFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

        void hideToggle();

        void hideFab();
    }
}

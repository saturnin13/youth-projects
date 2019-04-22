package uk.co.jumprapp.photos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        PhotoListFragment.OnPhotoListFragmentInteractionListener,
        SinglePhotoFragment.OnSinglePhotoFragmentInteractionListener,
        SwiperFragment.OnSwiperFragmentInteractionListener,

        LoginFragment.OnLoginFragmentInteractionListener,
        SwiperFragment.ScreenSlidePageFragment.OnScreenSlidePagerAdapterInteractionListener {


    public static final String CAMERA_IMAGE_BUCKET_NAME = Environment.getExternalStorageDirectory().toString() + "/DCIM";

    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 0;
    DisplayMetrics displayMetrics;
    private List<Photo> photoList;
    private List<SharePhoto> sharePhotoList;
    private boolean selectMode;
    private int width;
    private Switch aSwitch;
    private int numSelected;
    private FloatingActionButton fab;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private List<Photo> selected;
    private List<Bitmap> toSend;

    /**
     * Matches code in MediaProvider.computeBucketValues. Should be a common
     * function.
     */
    public static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public List<Photo> getCameraImages(String Path) {
        File f = new File(Path);
        File[] files = f.listFiles();
        List<Photo> images = new ArrayList<Photo>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                int mid = files[i].getName().lastIndexOf(".");
                String ext = files[i].getName().substring(mid + 1, files[i].getName().length());

                if (ext.equalsIgnoreCase("jpg")
                        || ext.equalsIgnoreCase("png")
                        || ext.equalsIgnoreCase("jpeg")
                        || ext.equalsIgnoreCase("gif")) {
                    Photo photo = new Photo(files[i].getAbsolutePath());
                    images.add(photo);
                }
            }
            /* duplicate removed */
            else if (files[i].getName().equals(".thumbnails")) {
                continue;
            } else
                images.addAll(getCameraImages(files[i].getAbsolutePath()));
        }

        return images;
    }


    //  static { System.loadLibrary("opencv_java3"); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        launchLoginFragment();
        toSend = new ArrayList<>();
        sharePhotoList = new ArrayList<>();
        selected = new ArrayList<>();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };

        if (AccessToken.getCurrentAccessToken() != null) {
            startHome();
        } else {
            launchLoginFragment();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    private void showAllPhotos() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment instanceof PhotoListFragment) {
            PhotoListFragment photoListFragment = (PhotoListFragment) fragment;
            photoListFragment.showAllPhotos();
        }
    }

    private void filterOutBadPhotos() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment instanceof PhotoListFragment) {
            PhotoListFragment photoListFragment = (PhotoListFragment) fragment;
            photoListFragment.filterOutBadPhotos();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            refreshPhotoList();
        }
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public List<Photo> getSelected() {
        return selected;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startHome() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        numSelected = 0;
        selectMode = false;

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        aSwitch = (Switch) findViewById(R.id.filter_switch);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterOutBadPhotos();
                } else {
                    showAllPhotos();
                }
            }
        });
        displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Log.i("GOTR PERN", "BAMM");
            photoList = getCameraImages(CAMERA_IMAGE_BUCKET_NAME);
            Log.i("SAFEEEE", String.valueOf(photoList.size()));
            launchPhotoListFragment();
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("getSelected SIZEEE", String.valueOf(getSelected().size()));
                if (!getSelected().isEmpty()) {
                    for (Photo photo : getSelected()) {
                        Glide.with(MainActivity.this)
                                .load(photo.getFile())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap image, GlideAnimation glideAnimation) {
                                        addToSendList(image);
                                    }
                                });
                    }

                } else {
                    Toast.makeText(MainActivity.this, "No photos selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Log.i("GOT hEREE", "awd");
    }

    public synchronized void addToSendList(Bitmap bitmap) {
        toSend.add(bitmap);
        if (toSend.size() == getSelected().size()) {
            for (Bitmap image : toSend) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                sharePhotoList.add(photo);
            }
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhotos(sharePhotoList)
                    .build();
            ShareDialog.show(this, content);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            launchPhotoListFragment();
        } else if (id == R.id.nav_swiper) {
            launchSwiperFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void launchPhotoListFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, PhotoListFragment.newInstance(3));
        ft.addToBackStack(null);
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_gallery);
    }

    private void launchSinglePhotoFragment(Photo photo) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, SinglePhotoFragment.newInstance(photo));
        ft.addToBackStack(null);
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_gallery);
    }

    private void launchSwiperFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, SwiperFragment.newInstance(photoList));
        ft.addToBackStack(null);
        ft.commit();
        navigationView.setCheckedItem(R.id.nav_swiper);
    }

    private void launchLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_container, LoginFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onPhotoListSingleClick(Photo photo) {
        launchSinglePhotoFragment(photo);
    }

    @Override
    public void refreshPhotos() {
        refreshPhotoList();
        launchPhotoListFragment();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public boolean isSelectMode() {
        return selectMode;
    }

    @Override
    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }

    @Override
    public void succCount() {
        numSelected++;
    }

    @Override
    public void decCount() {
        numSelected--;
    }

    @Override
    public void addToSelected(Photo photo) {
        if (!selected.contains(photo)) {
            selected.add(photo);
        }
    }

    @Override
    public void removeFromSelected(Photo photo) {
        if (selected.contains(photo)) {
            selected.remove(photo);
        }
    }

    public int numberSelected() {
        return numSelected;
    }

    @Override
    public void showToggle() {
        aSwitch.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFab() {
        fab.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    photoList = getCameraImages(CAMERA_IMAGE_BUCKET_NAME);
                    Log.i("PHHHOOOOTT SIIIIZE", String.valueOf(photoList.size()));
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                    if (fragment instanceof PhotoListFragment) {
                        PhotoListFragment photoListFragment = (PhotoListFragment) fragment;
                        photoListFragment.onPhotoListChanged();
                    }
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    finish();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void refreshPhotoList() {
        photoList = getCameraImages(CAMERA_IMAGE_BUCKET_NAME);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment instanceof PhotoListFragment) {
            PhotoListFragment photoListFragment = (PhotoListFragment) fragment;
            photoListFragment.onPhotoListChanged();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void hideToggle() {
        aSwitch.setVisibility(View.GONE);
    }

    @Override
    public void hideFab() {
        fab.hide();
    }

    @Override
    public void onSwiperFragmentInteraction() {

    }

    private void syncFrags() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment instanceof PhotoListFragment) {
            PhotoListFragment photoListFragment = (PhotoListFragment) fragment;
            navigationView.setCheckedItem(R.id.nav_gallery);
        } else if (fragment instanceof SwiperFragment) {
            SwiperFragment swiperFragment = (SwiperFragment) fragment;
            navigationView.setCheckedItem(R.id.nav_swiper);

        } else if (fragment instanceof SinglePhotoFragment) {
            SinglePhotoFragment singlePhotoFragment = (SinglePhotoFragment) fragment;
            navigationView.setCheckedItem(R.id.nav_gallery);
        }
    }
}

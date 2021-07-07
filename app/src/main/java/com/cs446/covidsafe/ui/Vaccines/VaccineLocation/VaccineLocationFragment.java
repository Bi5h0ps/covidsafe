package com.cs446.covidsafe.ui.Vaccines.VaccineLocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.cs446.covidsafe.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class VaccineLocationFragment extends Fragment {

    public VaccineLocationFragment() {
        // Required empty public constructor
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        //43.47425619407127, -80.5362798774214
        public void onMapReady(GoogleMap googleMap) {
            LatLng waterloo = new LatLng(43.4643, -80.5204);
            CameraUpdateFactory.zoomTo(15);

            googleMap.addMarker(new MarkerOptions()
                    .position(waterloo)
                    .title("My place")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

            //43.47195552353127, -80.53870003230735
            LatLng PharmasaveCampus = new LatLng(43.47195552353127, -80.53870003230735);
            googleMap.addMarker(new MarkerOptions()
                    .position(PharmasaveCampus)
                    .title("PharmasaveCampus"));
            //43.47581152967209, -80.52459460788678
            LatLng KWUniversityPharmacy = new LatLng(43.47581152967209, -80.52459460788678);
            googleMap.addMarker(new MarkerOptions()
                    .position(KWUniversityPharmacy)
                    .title("KWUniversityPharmacy"));
            //43.486168172976534, -80.54034052263884
            LatLng AlphaMedPharmacy = new LatLng(43.486168172976534, -80.54034052263884);
            googleMap.addMarker(new MarkerOptions()
                    .position(AlphaMedPharmacy)
                    .title("AlphaMedPharmacy"));
            //43.462221187511126, -80.53691257021013
            LatLng PharmasaveWestmountPlace = new LatLng(43.462221187511126, -80.53691257021013);
            googleMap.addMarker(new MarkerOptions()
                    .position(PharmasaveWestmountPlace)
                    .title("PharmasaveWestmountPlace"));
            //43.46424170820742, -80.52349209534587
            LatLng ShoppersDrugMart = new LatLng(43.46424170820742, -80.52349209534587);
            googleMap.addMarker(new MarkerOptions()
                    .position(ShoppersDrugMart)
                    .title("ShoppersDrugMart"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(waterloo,13));



        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vaccine_location, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

    }

}
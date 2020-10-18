package com.ap.group38.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ap.group38.R;
import com.ap.group38.adapter.BookingAdapter;
import com.ap.group38.adapter.OnClickListener;


public class BookingFragment extends Fragment implements OnClickListener {

    private RecyclerView recyclerView;
    private Spinner source;
    private Spinner destination;
    private BookingAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        source = view.findViewById(R.id.spSource);
        destination = view.findViewById(R.id.spDestination);
        recyclerView = view.findViewById(R.id.rvBooking);
        progressBar = view.findViewById(R.id.pdBooking);
        adapter = new BookingAdapter(this);


        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.source, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> dAdapter = ArrayAdapter.createFromResource(requireActivity(),
                R.array.destination, android.R.layout.simple_spinner_item);


        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        source.setAdapter(sAdapter);
        destination.setAdapter(dAdapter);


        Button book = view.findViewById(R.id.btnBookNow);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer();
            }
        });

        return view;
    }

    private void setupRecyclerView() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
    }

    public void timer() {

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                progressBar.setVisibility(View.VISIBLE);
            }

            public void onFinish() {
                setupRecyclerView();
            }
        }.start();
    }


    @Override
    public void onClick(int pos) {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_nav_booking_to_confirmShipFragment);
    }
}


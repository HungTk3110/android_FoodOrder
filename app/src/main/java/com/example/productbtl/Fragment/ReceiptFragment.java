package com.example.productbtl.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.productbtl.Activity.MainActivity;
import com.example.productbtl.Activity.RegesterActivity;
import com.example.productbtl.Adapter.receipt_Adapter;
import com.example.productbtl.Object.receipt;
import com.example.productbtl.R;
import com.example.productbtl.myinterface.buttonClickListenner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ReceiptFragment extends Fragment {

    private RecyclerView rcv_receipt;
    private MainActivity mainActivity;
    private receipt_Adapter receipt_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);

        rcv_receipt = view.findViewById(R.id.rcv_receipt);
        mainActivity = (MainActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv_receipt.setLayoutManager(linearLayoutManager);
        receipt_adapter = new receipt_Adapter(getListDataFromFireBase(), new buttonClickListenner() {
            @Override
            public void onClickDeleteItem(receipt receipt) {
                deleteItem(receipt);
            }
        });
        rcv_receipt.setAdapter(receipt_adapter);


        return view;
    }

    private void deleteItem(receipt receipt) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uer = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("Data_User:"+uer+"/"+receipt.getId());
        myRef.removeValue();
        receipt_adapter.setData(getListDataFromFireBase());
    }

    private List<receipt> getListDataFromFireBase(){
        List<receipt> list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uer = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = database.getReference("Data_User:"+uer);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    receipt mreceipt = dataSnapshot.getValue(receipt.class);
                    list.add(mreceipt);
                }
                // đảo ngược list
                Collections.reverse(list);
                receipt_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity() ,"Lỗi" , Toast.LENGTH_LONG).show();            }
        });
        return list;
    }
}
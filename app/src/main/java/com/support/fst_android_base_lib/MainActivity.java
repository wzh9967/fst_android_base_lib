package com.support.fst_android_base_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.support.fst_android_base_lib.fst.FstWallet;

public class MainActivity extends AppCompatActivity {
    private FstWallet mFstWallet;
    final private static String node = "http://101.200.174.239:7545";
    final private static String address = "0x1e99e9720409355B64A7c9582975d2a73f594e83";
    final private static String contract ="0xc19323c4c4298673b41c6847ba937b5e6d9d77db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFstWallet = FstWallet.getInstance();
        mFstWallet.init(this);
        mFstWallet.initStorm3Provider(node);
        mFstWallet.initContract(contract,address,node);
    }

    public FstWallet getmMoacWallet() {
        return mFstWallet;
    }
}
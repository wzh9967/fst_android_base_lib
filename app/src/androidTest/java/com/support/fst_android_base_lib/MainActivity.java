package com.support.fst_android_base_lib;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.support.fst_android_base_lib.base.WCallback;
import com.support.fst_android_base_lib.fst.FstWallet;
import com.support.fst_android_base_lib.util.GsonUtil;

import org.junit.Assert;

public class MainActivity extends Activity {
    private FstWallet mFstWallet;
    private static final String TAG = "MainActivity";
    final private static String node = "http://101.200.174.239:7545";
    private static final String hash = "0x2bbf4c21249467f8541689c2bde773d2729da1d4d1daa08bf0c38e3524ef7c93";
    final private static String address = "0x1e99e9720409355B64A7c9582975d2a73f594e83";
    final private static String contract ="0xc19323c4c4298673b41c6847ba937b5e6d9d77db";
    final private static String secret = "0x6defd9e9359bfcfd3c13266378b15b299e8ff6ec2cf25d948f78ec2d65887b88";
    final private static String password = "Qq123456";
    final private static String IBAN = "XE703KOMUB7RABL33RE06B2925QKY3B63K3";
    final private static String words = "follow horror traffic pipe ladder relief glare emotion thumb equip script tornado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFstWallet = FstWallet.getInstance();
        mFstWallet.init(this);
        mFstWallet.initStorm3Provider(node);
        mFstWallet.initContract(contract,address,node);
/*
        mFstWallet.getErc20Balance(contract,address,new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, "ret : "+ ret);
                Log.d(TAG, "getErc20Balance = "+ data.getString("balance",""));
            }
        });
        mFstWallet.getGasPrice(new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, "ret : "+ ret);
                Log.d(TAG, "getGasPrice = "+ data.getString("GasPrice",""));
            }
        });


        mFstWallet.getTransactionDetail(hash, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, "ret: "+ ret);
                Log.d(TAG, ": getTransactionDetail = "+ data.toString());
            }
        });

        mFstWallet.getTransactionReceipt(hash, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, "ret: "+ ret);
                Log.d(TAG, "getTransactionReceipt ="+ data.toString());
            }
        });

        mFstWallet.getBalance(address, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, " ret = "+ret);
                Log.d(TAG, "getBalance: data "+ data);
            }
        });


        mFstWallet.importWords(words,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "importWords: data "+ data);
            }
        });

        mFstWallet.importSecret(secret,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "importSecret: data "+ data);
            }
        });

        mFstWallet.toIban(address, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "toIban: data "+ data);
            }
        });

        mFstWallet.fromIban(IBAN, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "fromIban: data "+ data);
            }
        });

        mFstWallet.isValidSecret(secret, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "isValidSecret: data "+ data);
            }
        });

        mFstWallet.isValidAddress(address, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "isValidAddress: data "+ data);
            }
        });

        mFstWallet.createWallet(new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "createWallet: data "+ data);
            }
        });

        mFstWallet.toIban("0x1e99e9720409355B64A7c9582975d2a73f594e83", new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Log.d(TAG, ": ret = "+ret);
                Log.d(TAG, "toIban: data "+ data);
            }
        });

        GsonUtil data = new GsonUtil("{}");
        data.putString("address","0x981d4bc976c221b3b42270be6dcab72d37d2e0cd");
        data.putString("to",address);
        data.putString("secret","0x1a0ad31a04ed4dbcec91a8a54c0d87187b50ab60e03139f404533332e9b31917");
        data.putString("value","100000000000000000");//0.1
        data.putString("gasLimit","22000");
        data.putDouble("gasPrice",10000000000.0);
        data.putString("data","");
        mFstWallet.sendTransaction(data,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Log.d(TAG, "completion: ret = "+ret);
                String hash = data.getString("hash","");
                Log.d(TAG, "completion: hash = "+hash);
            }
        });



        GsonUtil data2 = new GsonUtil("{}");
        data2.putString("address","0x981d4bc976c221b3b42270be6dcab72d37d2e0cd");
        data2.putString("to",address);
        data2.putString("secret","0x1a0ad31a04ed4dbcec91a8a54c0d87187b50ab60e03139f404533332e9b31917");
        data2.putString("value","10000000000000000000");//0.1
        data2.putString("gasLimit","700000");
        data2.putDouble("gasPrice",10000000000.0);
        data2.putString("data","");
        data2.putString("contract",contract);
        mFstWallet.sendErc20Transaction(data2,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Log.d(TAG, "completion: ret = "+ret);
                String hash = data.getString("hash","");
                Log.d(TAG, "completion: hash = "+hash);
            }
        });

 */
    }

    public FstWallet getmMoacWallet() {
        return mFstWallet;
    }
}
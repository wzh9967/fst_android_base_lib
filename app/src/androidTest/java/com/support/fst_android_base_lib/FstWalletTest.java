package com.support.fst_android_base_lib;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.support.fst_android_base_lib.base.WCallback;
import com.support.fst_android_base_lib.fst.FstWallet;
import com.support.fst_android_base_lib.util.GsonUtil;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class FstWalletTest extends TestCase {
    final private static String node = "http://101.200.174.239:7545";
    private static final String hash = "0x2bbf4c21249467f8541689c2bde773d2729da1d4d1daa08bf0c38e3524ef7c93";
    final private static String address = "0x1e99e9720409355B64A7c9582975d2a73f594e83";
    final private static String contract ="0xc19323c4c4298673b41c6847ba937b5e6d9d77db";
    final private static String secret = "0x6defd9e9359bfcfd3c13266378b15b299e8ff6ec2cf25d948f78ec2d65887b88";
    final private static String password = "Qq123456";
    final private static String IBAN = "XE703KOMUB7RABL33RE06B2925QKY3B63K3";
    final private static String words = "follow horror traffic pipe ladder relief glare emotion thumb equip script tornado";
    private static GsonUtil TxData;


    private FstWallet mFstWallet;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mFstWallet = mActivityRule.getActivity().getmMoacWallet();
        TxData = new GsonUtil("");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateWallet() {
        mFstWallet.createWallet(new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotNull(data.getString("address", ""));
                Assert.assertNotNull(data.getString("secret", ""));
                Assert.assertNotNull(data.getString("words", ""));
            }
        });
    }

    @Test
    public void testIsValidAddress() {
        mFstWallet.isValidAddress(address, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(data.getString("isAddress",""),"true");
            }
        });
    }



    @Test
    public void testIsValidSecret() {
        mFstWallet.isValidSecret(secret, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(data.getString("isSecret",""),"true");
            }
        });
    }

    @Test
    public void testImportSecret() {
        mFstWallet.importSecret(secret,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(address,data.getString("address",""));
                Assert.assertEquals(secret,data.getString("secret",""));
            }
        });
    }

    @Test
    public void testImportWords() {
        mFstWallet.importWords(words,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(address,data.getString("address",""));
                Assert.assertEquals(secret,data.getString("secret",""));
            }
        });

    }

    @Test
    public void testToIban() {
        mFstWallet.toIban(address, new WCallback() {
            @Override
            public void completion(int ret,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String iban = data.getString("iban","");
                Assert.assertEquals(IBAN, iban);
            }
        });
    }

    @Test
    public void testFromIban() {
        mFstWallet.fromIban(IBAN, new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String address1 = data.getString("address","");
                Assert.assertEquals(address.toLowerCase(), address1.toLowerCase());
            }
        });
    }

    @Test
    public void testGetBalance() {
        mFstWallet.getBalance(address,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String balance = data.getString("balance","");
                Assert.assertNotEquals(balance,"");
            }
        });
    }

    @Test
    public void testSendErc20Transaction() {
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
                Assert.assertEquals(ret, 0);
                String hash = data.getString("hash","");
                Assert.assertNotEquals(hash,"");
            }
        });

    }

    @Test
    public void testSendTransaction() {
        GsonUtil data = new GsonUtil("{}");
        data.putString("address","0x981d4bc976c221b3b42270be6dcab72d37d2e0cd");
        data.putString("to",address);
        data.putString("secret","0x1a0ad31a04ed4dbcec91a8a54c0d87187b50ab60e03139f404533332e9b31917");
        data.putString("value","1000000000000000");//0.1
        data.putString("gasLimit","22000");
        data.putDouble("gasPrice",10000000000.0);
        data.putString("data","");
        mFstWallet.sendTransaction(data,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String hash = data.getString("hash","");
                Assert.assertNotEquals(hash,"");
            }
        });
    }

    @Test
    public void testGetErc20Balance(){
        mFstWallet.getErc20Balance(contract,address,new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotEquals(data.getString("balance",""),"");
            }
        });
    }

    @Test
    public void testGetGasPrice() {
        mFstWallet.getGasPrice(new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String GasPrice = data.getString("GasPrice","");
                Assert.assertNotEquals(GasPrice,"");

            }
        });
    }

    @Test
    public void testGetTransactionDetail() {

        mFstWallet.getTransactionDetail(hash,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotNull(data.toString());
            }
        });

    }

    @Test
    public void testGetTransactionReceipt() {
        mFstWallet.getTransactionReceipt(hash,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotNull(data.toString());
            }
        });
    }

}
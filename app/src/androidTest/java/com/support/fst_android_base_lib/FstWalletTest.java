package com.support.fst_android_base_lib;

import android.os.Handler;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;



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

import java.util.concurrent.CountDownLatch;

//测试时 发送erc20交易和原生交易将会导致重复发送。仅一个会被确认。分开测试可以解决这个问题。
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
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.createWallet(new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotEquals(data.getString("address", ""),"");
                Assert.assertNotEquals(data.getString("secret", ""),"");
                Assert.assertNotEquals(data.getString("words", ""),"");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsValidAddress() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.isValidAddress(address, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(data.getString("isAddress",""),"true");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsValidSecret() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.isValidSecret(secret, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(data.getString("isSecret",""),"true");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImportSecret() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.importSecret(secret,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(address,data.getString("address",""));
                Assert.assertEquals(secret,data.getString("secret",""));
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testImportWords() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.importWords(words,password, new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertEquals(address,data.getString("address",""));
                Assert.assertEquals(secret,data.getString("secret",""));
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testToIban() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.toIban(address, new WCallback() {
            @Override
            public void completion(int ret,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String iban = data.getString("Iban","");
                Assert.assertEquals(IBAN, iban);
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFromIban() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.fromIban(IBAN, new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String address1 = data.getString("address","");
                Assert.assertEquals(address.toLowerCase(), address1.toLowerCase());
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBalance() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.getBalance(address,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String balance = data.getString("balance","");
                Assert.assertNotEquals(balance,"");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSendErc20Transaction() {
        final CountDownLatch latch = new CountDownLatch(1);
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
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSendTransaction() {
        final CountDownLatch latch = new CountDownLatch(1);
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
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetErc20Balance(){
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.getErc20Balance(contract,address,new WCallback() {
            @Override
            public void completion(int ret, GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotEquals(data.getString("balance",""),"");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetGasPrice() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.getGasPrice(new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                String GasPrice = data.getString("GasPrice","");
                Assert.assertNotEquals(GasPrice,"");
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTransactionDetail() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.getTransactionDetail(hash,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotNull(data.toString());
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetTransactionReceipt() {
        final CountDownLatch latch = new CountDownLatch(1);
        mFstWallet.getTransactionReceipt(hash,new WCallback() {
            @Override
            public void completion(int ret ,GsonUtil data) {
                Assert.assertEquals(ret, 0);
                Assert.assertNotNull(data.toString());
                latch.countDown();
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
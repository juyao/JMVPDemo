package com.juyao.jmvp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;

import com.juyao.jmvp.Config;
import com.juyao.jmvp.kit.KnifeKit;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by juyao on 2017/11/23.
 */

public abstract class JActivity<P extends IPresent> extends RxAppCompatActivity implements IView<P> {
    private VDelegate vDelegate;
    private P p;
    protected Activity context;

    private RxPermissions rxPermissions;

    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindUI(null);
        }
        initData(savedInstanceState);

    }

    @Override
    public void bindUI(View rootView) {
        unbinder = KnifeKit.Companion.bind(this);
    }

    protected VDelegate getvDelegate() {
        if (vDelegate == null) {
            vDelegate = VDelegateBase.create(context);
        }
        return vDelegate;
    }

    protected P getP() {
        if (p == null) {
            p = newP();
            if (p != null) {
                p.attachV(this);
            }
        }
        return p;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getvDelegate().resume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        getvDelegate().pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        getvDelegate().destory();
        p = null;
        vDelegate = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getOptionsMenuId() > 0) {
            getMenuInflater().inflate(getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected RxPermissions getRxPermissions() {
        rxPermissions = new RxPermissions(this);
        rxPermissions.setLogging(Config.Companion.getDEV());
        return rxPermissions;
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }
}

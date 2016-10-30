package com.example.luizh.projeto_eorl.domain.util;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.luizh.projeto_eorl.R;

import java.util.zip.Inflater;


/**
 * Created by luiz on 10/07/16.
 */
public class DrawerControl extends android.support.v7.app.ActionBarDrawerToggle
{
    View txtEmailNav;
    User user;

    public DrawerControl(Activity activity, DrawerLayout drawerLayout,
                         Toolbar toolbar, @StringRes int openDrawerContentDescRes,
                         @StringRes int closeDrawerContentDescRes)
    {
        super(activity, drawerLayout, toolbar ,openDrawerContentDescRes, closeDrawerContentDescRes);
    }

    @Override
    public void onDrawerOpened(View drawerView)
    {
        super.onDrawerOpened(drawerView);
    }
}

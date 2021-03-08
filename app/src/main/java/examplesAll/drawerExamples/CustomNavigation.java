package examplesAll.drawerExamples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.self.demoaall.R;
import com.self.demoaall.databinding.ActivityCustomNavigationBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

public class CustomNavigation extends AppCompatActivity {
    private ActivityCustomNavigationBinding viewBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = DataBindingUtil.setContentView(CustomNavigation.this, R.layout.activity_custom_navigation);

        setupToolbar();
        setupDrawerToggle();

        viewBinding.leftDrawerMenu.clickAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(CustomNavigation.this, AboutUs.class));
            }
        });
    }

    /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(false); is used to hide the default back button
         */
    private void setupToolbar() {
        setSupportActionBar(viewBinding.contentMain.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupDrawerToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, viewBinding.drawerLayout, viewBinding.contentMain.toolbar,
                R.string.openDrawer, R.string.closedDrawer);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        actionBarDrawerToggle.syncState();
    }
}
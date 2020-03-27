package com.example.liupingmysuperapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener {
    AppBarConfiguration BarConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BarConfig = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, BarConfig);
    }
    @Override
    public void onListFragmentInteraction(CatFact item){
        Fragment navHost = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavDirections action = ListFragmentDirections.actionListFragmentToDetailFragment(item.fact);
        NavHostFragment.findNavController(navHost).navigate(action);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, BarConfig)
                || super.onSupportNavigateUp();
    }

}

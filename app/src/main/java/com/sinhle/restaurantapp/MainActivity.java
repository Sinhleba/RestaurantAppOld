package com.sinhle.restaurantapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sinhle.restaurantapp.ui.Common;
import com.sinhle.restaurantapp.ui.ViewPagerAdapter;
import com.sinhle.restaurantapp.ui.database.DatabaseHandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sinhle.restaurantapp.databinding.ActivityMainBinding;
import com.sinhle.restaurantapp.ui.drink.DrinkFragment;
import com.sinhle.restaurantapp.ui.employee.EmployeeFragment;
import com.sinhle.restaurantapp.ui.entity.Book;
import com.sinhle.restaurantapp.ui.entity.Drink;
import com.sinhle.restaurantapp.ui.home.BookFragment;
import com.sinhle.restaurantapp.ui.setting.SettingFragment;
import com.sinhle.restaurantapp.ui.statistic.StatisticFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHandler databaseHandler;

    private static final int MENU_ITEM_ID_ONE =1;
    private static final int MENU_ITEM_ID_TWO =2;
    private static final int MENU_ITEM_ID_THREE =3;
    private static final int MENU_ITEM_ID_FOUR =4;
    private static final int MENU_ITEM_ID_FIVE =5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null,DatabaseHandler.DATABASE_VERSION);
        if (!Common.getBoolean(MainActivity.this, Common.CREATE_DATABASE)) {
            addDatabase();
            Common.putBoolean(MainActivity.this, Common.CREATE_DATABASE, true);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.vpData.setCurrentItem(item.getItemId()-1);

                return true;
            }

        });
        Menu menu = binding.navView.getMenu();
        ArrayList<Fragment> listFragment = new ArrayList<>();
        if (Common.getBoolean(MainActivity.this, Common.IS_MANAGER)) {
            listFragment.add(BookFragment.newInstance());
            listFragment.add(DrinkFragment.newInstance());
            listFragment.add(EmployeeFragment.newInstance());
            listFragment.add(StatisticFragment.newInstance());
            listFragment.add(SettingFragment.newInstance());

            menu.add(Menu.NONE, MENU_ITEM_ID_ONE, Menu.NONE, getString(R.string.title_book))
                    .setIcon(R.drawable.ic_home_black_24dp);
            menu.add(Menu.NONE, MENU_ITEM_ID_TWO, Menu.NONE, getString(R.string.title_menu))
                    .setIcon(R.drawable.ic_baseline_restaurant_menu_24);
            menu.add(Menu.NONE, MENU_ITEM_ID_THREE, Menu.NONE, getString(R.string.employee))
                    .setIcon(R.drawable.ic_baseline_person_pin_24);
            menu.add(Menu.NONE, MENU_ITEM_ID_FOUR, Menu.NONE, getString(R.string.title_statistical))
                    .setIcon(R.drawable.ic_baseline_edit_note_24);
            menu.add(Menu.NONE, MENU_ITEM_ID_FIVE, Menu.NONE, getString(R.string.setting))
                    .setIcon(R.drawable.ic_baseline_settings_24);

        }else{
            listFragment.add(BookFragment.newInstance());
            listFragment.add(SettingFragment.newInstance());
            menu.add(Menu.NONE, MENU_ITEM_ID_ONE, Menu.NONE, getString(R.string.title_book))
                    .setIcon(R.drawable.ic_home_black_24dp);
            menu.add(Menu.NONE, MENU_ITEM_ID_TWO, Menu.NONE, getString(R.string.setting))
                    .setIcon(R.drawable.ic_baseline_settings_24);
        }

        ViewPagerAdapter adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager(), listFragment);
        binding.vpData.setOffscreenPageLimit(listFragment.size());
        binding.vpData.setAdapter(adapterViewPager);
    }


    private void addDatabase() {
        try{
            //10 bàn
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            databaseHandler.addBook(new Book());
            //menu
            databaseHandler.addDrink(new Drink("Trà sữa truyền thống",0,20000));
            databaseHandler.addDrink(new Drink("Cam ép",0,20000));
            databaseHandler.addDrink(new Drink("Redbull",0,15000));
            databaseHandler.addDrink(new Drink("Sữa chua",0,10000));
            databaseHandler.addDrink(new Drink("Cà phê đen",0,12000));
            databaseHandler.addDrink(new Drink("Cà phê sữa",0,15000));
            databaseHandler.addDrink(new Drink("Trà sữa Thái xanh",0,25000));
            databaseHandler.addDrink(new Drink("Trà sữa trân châu đường đen",0,25000));
            databaseHandler.addDrink(new Drink("Cappuccino",0,50000));
            databaseHandler.addDrink(new Drink("Nước ngọt Sting",0,12000));

            databaseHandler.addDrink(new Drink("Khăn lạnh",0,3000));
            databaseHandler.addDrink(new Drink("Trà đá",0,3000));

            databaseHandler.addDrink(new Drink("Bánh mì Thịt chả",0,20000));
            databaseHandler.addDrink(new Drink("Hủ tiếu mì",0,30000));
            databaseHandler.addDrink(new Drink("Cơm sườn",0,25000));
            databaseHandler.addDrink(new Drink("Cơm sườn bì chả",0,400000));
            databaseHandler.addDrink(new Drink("Bánh mì bò kho",0,30000));
            databaseHandler.addDrink(new Drink("Phở bò",0,40000));
            databaseHandler.addDrink(new Drink("Bì thêm",0,5000));
            databaseHandler.addDrink(new Drink("Sườn thêm",0,20000));
            databaseHandler.addDrink(new Drink("Chả thêm",0,20000));
            databaseHandler.addDrink(new Drink("Lẩu Thái đặc biệt",0,350000));






        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
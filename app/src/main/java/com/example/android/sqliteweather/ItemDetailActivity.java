package com.example.android.sqliteweather;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sqliteweather.data.FilmItem;
import com.example.android.sqliteweather.data.PeopleItem;
import com.example.android.sqliteweather.data.PlanetItem;
import com.example.android.sqliteweather.data.SpeciesItem;
import com.example.android.sqliteweather.data.StarshipItem;
import com.example.android.sqliteweather.data.VehicleItem;

import java.util.Arrays;


public class ItemDetailActivity extends AppCompatActivity {

    private String mCategory;
    private String mName;

    public PlanetItem planetItem;
    private PeopleItem peopleItem;
    private FilmItem filmItem;
    private SpeciesItem speciesItem;
    private StarshipItem starshipItem;
    private VehicleItem vehicleItem;

    private boolean planetIsSaved = false;
    private boolean filmIsSaved = false;
    private boolean personIsSaved = false;
    private boolean speciesIsSaved = false;
    private boolean starshipIsSaved = false;
    private boolean vehicleIsSaved = false;

    //at most need 16 text views
    private TextView mDataTV_1;
    private TextView mDataTV_2;
    private TextView mDataTV_3;
    private TextView mDataTV_4;
    private TextView mDataTV_5;
    private TextView mDataTV_6;
    private TextView mDataTV_7;
    private TextView mDataTV_8;
    private TextView mDataTV_9;
    private TextView mDataTV_10;
    private TextView mDataTV_11;
    private TextView mDataTV_12;
    private TextView mDataTV_13;
    private TextView mDataTV_14;
    private TextView mDataTV_15;
    private TextView mDataTV_16;

    private ImageView mFavoriteImage;

    private FavoritesViewModel mFavoritesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        planetItem = null;
        peopleItem = null;
        filmItem = null;
        speciesItem = null;
        starshipItem = null;
        vehicleItem = null;

        mDataTV_1 = findViewById(R.id.detail_1_tv);
        mDataTV_2 = findViewById(R.id.detail_2_tv);
        mDataTV_3 = findViewById(R.id.detail_3_tv);
        mDataTV_4 = findViewById(R.id.detail_4_tv);
        mDataTV_5 = findViewById(R.id.detail_5_tv);
        mDataTV_6 = findViewById(R.id.detail_6_tv);
        mDataTV_7 = findViewById(R.id.detail_7_tv);
        mDataTV_8 = findViewById(R.id.detail_8_tv);
        mDataTV_9 = findViewById(R.id.detail_9_tv);
        mDataTV_10 = findViewById(R.id.detail_10_tv);
        mDataTV_11 = findViewById(R.id.detail_11_tv);
        mDataTV_12 = findViewById(R.id.detail_12_tv);
        mDataTV_13 = findViewById(R.id.detail_13_tv);
        mDataTV_14 = findViewById(R.id.detail_14_tv);
        mDataTV_15 = findViewById(R.id.detail_15_tv);
        mDataTV_16 = findViewById(R.id.detail_16_tv);

        mFavoriteImage = findViewById(R.id.favorite_IV);

        Intent intent = getIntent();
        mCategory = intent.getStringExtra("category");
        mName = getItemFromCategory(intent);
        getSupportActionBar().setTitle(mName);

        mFavoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        setObservers();
        checkFavorite();
    }

    public void setObservers(){
        if(mCategory.equals("Planets")){
            mFavoritesViewModel.getPlanetByName(mName).observe(this, new Observer<PlanetItem>() {
                @Override
                public void onChanged(@Nullable PlanetItem planetItem) {
                    if(planetItem != null){
                        planetIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        planetIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
        if(mCategory.equals("Films")){
            mFavoritesViewModel.getFilmByName(mName).observe(this, new Observer<FilmItem>() {
                @Override
                public void onChanged(@Nullable FilmItem filmItem) {
                    if(filmItem != null){
                        filmIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        filmIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
        if(mCategory.equals("People")){
            mFavoritesViewModel.getPersonByName(mName).observe(this, new Observer<PeopleItem>() {
                @Override
                public void onChanged(@Nullable PeopleItem peopleItem) {
                    if(peopleItem != null){
                        personIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        personIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
        if(mCategory.equals("Species")){
            mFavoritesViewModel.getSpeciesByName(mName).observe(this, new Observer<SpeciesItem>() {
                @Override
                public void onChanged(@Nullable SpeciesItem speciesItem) {
                    if(speciesItem != null){
                        speciesIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        speciesIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
        if(mCategory.equals("Starships")){
            mFavoritesViewModel.getStarshipByName(mName).observe(this, new Observer<StarshipItem>() {
                @Override
                public void onChanged(@Nullable StarshipItem starshipItem) {
                    if(starshipItem != null){
                        starshipIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        starshipIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
        if(mCategory.equals("Vehicles")){
            mFavoritesViewModel.getVehicleByName(mName).observe(this, new Observer<VehicleItem>() {
                @Override
                public void onChanged(@Nullable VehicleItem vehicleItem) {
                    if(vehicleItem != null){
                        vehicleIsSaved = true;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_favorite_black);
                    } else {
                        vehicleIsSaved = false;
                        mFavoriteImage.setImageResource(R.drawable.ic_action_empty_favorite_black);
                    }
                }
            });
        }
    }

    public void checkFavorite(){
        if(mCategory.equals("Planets")){
            mFavoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(planetItem != null){
                        if(!planetIsSaved){
                            mFavoritesViewModel.insertFavoritePlanet(planetItem);
                        } else{
                            mFavoritesViewModel.deleteFavoritePlanet(planetItem);
                        }
                    }
                }
            });
        }
        if(mCategory.equals("Films")){
            mFavoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(filmItem != null){
                        if(!filmIsSaved){
                            mFavoritesViewModel.insertFavoriteFilm(filmItem);
                        } else{
                            mFavoritesViewModel.deleteFavoriteFilm(filmItem);
                        }
                    }
                }
            });
        }
        if(mCategory.equals("People")){
            mFavoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(peopleItem != null){
                        if(!personIsSaved){
                            mFavoritesViewModel.insertFavoritePerson(peopleItem);
                        } else{
                            mFavoritesViewModel.deleteFavoritePerson(peopleItem);
                        }
                    }
                }
            });
        }
        if(mCategory.equals("Species")){
            if(speciesItem != null){
                if(!speciesIsSaved){
                    mFavoritesViewModel.insertFavoriteSpecies(speciesItem);
                } else{
                    mFavoritesViewModel.deleteFavoriteSpecies(speciesItem);
                }
            }
        }
        if(mCategory.equals("Starships")){
            mFavoriteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(starshipItem != null){
                        if(!starshipIsSaved){
                            mFavoritesViewModel.insertFavoriteStarship(starshipItem);
                        } else{
                            mFavoritesViewModel.deleteFavoriteStarship(starshipItem);
                        }
                    }
                }
            });
        }
        if(mCategory.equals("Vehicles")){
            if(vehicleItem != null){
                if(!vehicleIsSaved){
                    mFavoritesViewModel.insertFavoriteVechile(vehicleItem);
                } else{
                    mFavoritesViewModel.deleteFavoriteVehicle(vehicleItem);
                }
            }
        }
    }

    public String getItemFromCategory(Intent intent){
        //TODO: Set text in Strings res file
        //TODO: Search and set specific URLs in lists
        if(mCategory.equals("Planets")){
            planetItem = (PlanetItem) intent.getSerializableExtra("planet");
            mDataTV_1.setText("Rotation Period: " + planetItem.rotation_period);

            mDataTV_2.setText("Orbital Period: " + planetItem.orbital_period);

            mDataTV_3.setText("Diameter: " + planetItem.diameter);

            mDataTV_4.setText("Climate: " + planetItem.climate);

            mDataTV_5.setText("Gravity: " + planetItem.gravity);

            mDataTV_6.setText("Terrain: " + planetItem.terrain);

            mDataTV_7.setText("Surface Water: " + planetItem.surface_water);

            mDataTV_8.setText("Population: " + planetItem.population);

            if(planetItem.residents.length < 1){
                mDataTV_9.setText("Residents: None");
            } else {
                mDataTV_9.setText("Residents: " + Arrays.toString(planetItem.residents).replaceAll("\\[|\\]", ""));
            }

            if(planetItem.films.length < 1){
                mDataTV_10.setText("Films: None");
            } else {
                mDataTV_10.setText("Films: " + Arrays.toString(planetItem.films).replaceAll("\\[|\\]", ""));
            }

            mDataTV_11.setText("Date Created: " + planetItem.created);

            mDataTV_12.setText("Last Edited: " + planetItem.edited);
            return planetItem.name;
        }
        if(mCategory.equals("Films")){
            filmItem = (FilmItem) intent.getSerializableExtra("film");
            mDataTV_1.setText("Episode: " + filmItem.episode_id);

            mDataTV_2.setText("Director: " + filmItem.director);

            mDataTV_3.setText("Producer: " + filmItem.producer);

            mDataTV_4.setText("Release Date: " + filmItem.release_date);

            mDataTV_5.setText("Opening Crawl: " + filmItem.opening_crawl);

            if(filmItem.characters.length < 1){
                mDataTV_6.setText("Characters: None");
            } else {
                mDataTV_6.setText("Characters: " + Arrays.toString(filmItem.characters).replaceAll("\\[|\\]", ""));
            }

            if(filmItem.vehicles.length < 1){
                mDataTV_7.setText("Vehicles: None");
            } else {
                mDataTV_7.setText("Vehicles: " + Arrays.toString(filmItem.vehicles).replaceAll("\\[|\\]", ""));
            }

            if(filmItem.species.length < 1){
                mDataTV_8.setText("Species: None");
            } else {
                mDataTV_8.setText("Species: " + Arrays.toString(filmItem.species).replaceAll("\\[|\\]", ""));
            }

            mDataTV_9.setText("Date Created: " + filmItem.created);

            mDataTV_10.setText("Last Edited: " + filmItem.edited);
            return filmItem.title;
        }
        if(mCategory.equals("People")){
            peopleItem = (PeopleItem) intent.getSerializableExtra("person");
            mDataTV_1.setText("Height: " + peopleItem.height);

            mDataTV_2.setText("Mass: " + peopleItem.mass);

            mDataTV_3.setText("Hair Color: " + peopleItem.hair_color);

            mDataTV_4.setText("Skin Color: " + peopleItem.skin_color);

            mDataTV_5.setText("Eye Color: " + peopleItem.eye_color);

            mDataTV_6.setText("Birth Year: " + peopleItem.birth_year);

            mDataTV_7.setText("Gender: " + peopleItem.gender);

            mDataTV_8.setText("Homeworld: " +peopleItem.homeworld);

            if(peopleItem.films.length < 1){
                mDataTV_9.setText("Films: None");
            } else {
                mDataTV_9.setText("Films: " + Arrays.toString(peopleItem.films).replaceAll("\\[|\\]", ""));
            }

            if(peopleItem.species.length < 1){
                mDataTV_10.setText("Species: None");
            } else {
                mDataTV_10.setText("Species: " + Arrays.toString(peopleItem.species).replaceAll("\\[|\\]", ""));
            }

            if(peopleItem.vehicles.length < 1){
                mDataTV_11.setText("Vehicles: None");
            } else {
                mDataTV_11.setText("Vehicles: " + Arrays.toString(peopleItem.vehicles).replaceAll("\\[|\\]", ""));
            }

            if(peopleItem.starships.length < 1){
                mDataTV_12.setText("Starships: None");
            } else {
                mDataTV_12.setText("Starships: " + Arrays.toString(peopleItem.starships).replaceAll("\\[|\\]", ""));
            }

            mDataTV_13.setText("Date Created: " + peopleItem.created);

            mDataTV_14.setText("Last Edited: " + peopleItem.edited);
            return peopleItem.name;
        }
        if(mCategory.equals("Species")){
            speciesItem = (SpeciesItem) intent.getSerializableExtra("species");
            mDataTV_1.setText("Classification: " + speciesItem.classification);

            mDataTV_2.setText("Designation: " + speciesItem.designation);

            mDataTV_3.setText("Average Height: " + speciesItem.average_height);

            mDataTV_4.setText("Skin Colors: " + speciesItem.skin_colors);

            mDataTV_5.setText("Hair Colors: " + speciesItem.hair_colors);

            mDataTV_6.setText("Eye Colors: " + speciesItem.eye_colors);

            mDataTV_7.setText("Average Lifespan: " + speciesItem.average_lifespan);

            mDataTV_8.setText("Homeworld: " + speciesItem.homeworld);

            mDataTV_9.setText("Language: " + speciesItem.language);

            if(speciesItem.people.length < 1){
                mDataTV_10.setText("People: None");
            } else {
                mDataTV_10.setText("People: " + Arrays.toString(speciesItem.people).replaceAll("\\[|\\]", ""));
            }

            if(speciesItem.films.length < 1){
                mDataTV_11.setText("Films: None");
            } else {
                mDataTV_11.setText("Films: " + Arrays.toString(speciesItem.films).replaceAll("\\[|\\]", ""));
            }

            mDataTV_12.setText("Date Created: " + speciesItem.created);

            mDataTV_13.setText("Last Edited: " + speciesItem.edited);
            return speciesItem.name;
        }
        if(mCategory.equals("Vehicles")){
            vehicleItem = (VehicleItem) intent.getSerializableExtra("vehicle");
            mDataTV_1.setText("Model: " + vehicleItem.model);

            mDataTV_2.setText("Manufacturer: " + vehicleItem.manufacturer);

            mDataTV_3.setText("Cost in Credits: " + vehicleItem.cost_in_credits);

            mDataTV_4.setText("Length: " + vehicleItem.length);

            mDataTV_5.setText("Max Speed: " + vehicleItem.max_atmosphering_speed);

            mDataTV_6.setText("Crew: " + vehicleItem.crew);

            mDataTV_7.setText("Passengers: " + vehicleItem.passengers);

            mDataTV_8.setText("Cargo Capacity: " + vehicleItem.cargo_capacity);

            mDataTV_9.setText("Consumables: " + vehicleItem.consumables);

            mDataTV_10.setText("Vehicle Class: " + vehicleItem.vehicle_class);

            if(vehicleItem.pilots.length < 1){
                mDataTV_11.setText("Pilots: None");
            } else {
                mDataTV_11.setText("Pilots: " + Arrays.toString(vehicleItem.pilots).replaceAll("\\[|\\]", ""));
            }

            if(vehicleItem.films.length < 1){
                mDataTV_12.setText("Films: None");
            } else {
                mDataTV_12.setText("Films: " + Arrays.toString(vehicleItem.films).replaceAll("\\[|\\]", ""));
            }

            mDataTV_13.setText("Date Created: " + vehicleItem.created);

            mDataTV_14.setText("Last Edited: " + vehicleItem.edited);
            return vehicleItem.name;
        }
        if(mCategory.equals("Starships")){
            starshipItem = (StarshipItem) intent.getSerializableExtra("starship");
            mDataTV_1.setText("Model: " + starshipItem.model);

            mDataTV_2.setText("Manufacturer: " + starshipItem.manufacturer);

            mDataTV_3.setText("Cost in Credits: " + starshipItem.cost_in_credits);

            mDataTV_4.setText("Length: " + starshipItem.length);

            mDataTV_5.setText("Max Speed: " + starshipItem.max_atomosphering_speed);

            mDataTV_6.setText("Crew: " + starshipItem.crew);

            mDataTV_7.setText("Cargo Capacity: " + starshipItem.cargo_capacity);

            mDataTV_8.setText("Consumables: " + starshipItem.consumables);

            mDataTV_9.setText("Hyperdrive Rating: " + starshipItem.hyperdrive_rating);

            mDataTV_10.setText("MGLT: " + starshipItem.MGLT);

            mDataTV_11.setText("Starship Class: " + starshipItem.starship_class);

            if(starshipItem.pilots.length < 1){
                mDataTV_12.setText("Pilots: None");
            } else{
                mDataTV_12.setText("Pilots: " + Arrays.toString(starshipItem.pilots).replaceAll("\\[|\\]", ""));
            }
            if(starshipItem.films.length < 1){
                mDataTV_13.setText("Films: None");
            } else {
                mDataTV_13.setText("Films: " + Arrays.toString(starshipItem.films).replaceAll("\\[|\\]", ""));
            }

            mDataTV_14.setText("Date Created: " + starshipItem.created);

            mDataTV_15.setText("Last Edited: " + starshipItem.edited);
            return starshipItem.name;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.detail_action_favorite:
                Intent fav = new Intent(this, FavoritesActivity.class);
                startActivity(fav);
                return true;
            case android.R.id.home:
                Intent intent = new Intent(this, CategorySearchActivity.class);
                intent.putExtra("category", mCategory);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

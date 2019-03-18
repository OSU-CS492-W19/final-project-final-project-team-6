package com.example.android.sqliteweather.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class FavoritesRepository {

    private static String TAG = FavoritesRepository.class.getSimpleName();

    private FavoritesDao mFavoritesDao;

    public FavoritesRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mFavoritesDao = db.favoritesDao();
    }

    public LiveData<List<PeopleItem>> getPeopleFavorites(){
        return mFavoritesDao.getFavoritePeople();
    }

    public LiveData<List<FilmItem>> getFilmFavorites(){
        return mFavoritesDao.getFavoriteFilms();
    }

    public LiveData<List<StarshipItem>> getStarshipFavorites() {
        return mFavoritesDao.getFavoriteStarships();
    }

    public LiveData<List<PlanetItem>> getPlanetFavorites(){
        return mFavoritesDao.getFavoritePlanets();
    }

    public LiveData<List<SpeciesItem>> getSpeciesFavorites(){
        return mFavoritesDao.getFavoriteSpecies();
    }

    public LiveData<List<VehicleItem>> getVehicleFavorites(){return mFavoritesDao.getFavoriteVEhicles();}

    public void insertFavoritePlanet(PlanetItem planetItem){new InsertFavoritePlanetAsyncTask(mFavoritesDao).execute(planetItem);}

    public void insertFavoriteFilm(FilmItem filmItem){new InsertFavoriteFilmAsyncTask(mFavoritesDao).execute(filmItem);}

    public void insertFavoritePerson(PeopleItem peopleItem){new InsertFavoritePersonAsyncTask(mFavoritesDao).execute(peopleItem);}

    public void insertFavoriteSpecies(SpeciesItem speciesItem){new InsertFavoriteSpeciesAsyncTask(mFavoritesDao).execute(speciesItem);}

    public void insertFavoriteStarship(StarshipItem starshipItem){new InsertFavoriteStarshipAsyncTask(mFavoritesDao).execute(starshipItem);}

    public void insertFavoriteVehicle(VehicleItem vehicleItem){new InsertFavoriteVehicleAsyncTask(mFavoritesDao).execute(vehicleItem);}

    public void deleteFavoritePlanet(PlanetItem planetItem){new DeleteFavoritePlanetAsyncTask(mFavoritesDao).execute(planetItem);}

    public void deleteFavoriteFilm(FilmItem filmItem){new DeleteFavoriteFilmAsyncTask(mFavoritesDao).execute(filmItem);}

    public void deleteFavoritePerson(PeopleItem peopleItem){new DeleteFavoritePersonAsyncTask(mFavoritesDao).execute(peopleItem);}

    public void deleteFavoriteSpecies(SpeciesItem speciesItem){new DeleteFavoriteSpeciesAsyncTask(mFavoritesDao).execute(speciesItem);}

    public void deleteFavoriteStarship(StarshipItem starshipItem){new DeleteFavoriteStarshipAsyncTask(mFavoritesDao).execute(starshipItem);}

    public void deleteFavoriteVehicle(VehicleItem vehicleItem){new DeleteFavoriteVehicleAsyncTask(mFavoritesDao).execute(vehicleItem);}

    private static class InsertFavoritePlanetAsyncTask extends AsyncTask<PlanetItem, Void, Void> {
        private FavoritesDao mAsyncPlanetDao;

        InsertFavoritePlanetAsyncTask(FavoritesDao dao){
            mAsyncPlanetDao = dao;
        }

        @Override
        protected Void doInBackground(PlanetItem... planetItems) {
            mAsyncPlanetDao.insertPlanet(planetItems[0]);
            return null;
        }
    }

    private static class DeleteFavoritePlanetAsyncTask extends AsyncTask<PlanetItem, Void, Void> {
        private FavoritesDao mAsyncPlanetDao;

        DeleteFavoritePlanetAsyncTask(FavoritesDao dao){
            mAsyncPlanetDao = dao;
        }

        @Override
        protected Void doInBackground(PlanetItem... planetItems) {
            mAsyncPlanetDao.deletePlanet(planetItems[0]);
            return null;
        }
    }

    private static class InsertFavoriteFilmAsyncTask extends AsyncTask<FilmItem, Void, Void> {
        private FavoritesDao mAsyncFilmDao;

        InsertFavoriteFilmAsyncTask(FavoritesDao dao){
            mAsyncFilmDao = dao;
        }

        @Override
        protected Void doInBackground(FilmItem... filmItems) {
            mAsyncFilmDao.insertFilm(filmItems[0]);
            return null;
        }
    }

    private static class DeleteFavoriteFilmAsyncTask extends AsyncTask<FilmItem, Void, Void> {
        private FavoritesDao mAsyncFilmDao;

        DeleteFavoriteFilmAsyncTask(FavoritesDao dao){
            mAsyncFilmDao = dao;
        }

        @Override
        protected Void doInBackground(FilmItem... filmItems) {
            mAsyncFilmDao.deleteFilm(filmItems[0]);
            return null;
        }
    }

    private static class InsertFavoritePersonAsyncTask extends AsyncTask<PeopleItem, Void, Void> {
        private FavoritesDao mAsyncPersonDao;

        InsertFavoritePersonAsyncTask(FavoritesDao dao){
            mAsyncPersonDao = dao;
        }

        @Override
        protected Void doInBackground(PeopleItem... peopleItems) {
            mAsyncPersonDao.insertPerson(peopleItems[0]);
            return null;
        }
    }

    private static class DeleteFavoritePersonAsyncTask extends AsyncTask<PeopleItem, Void, Void> {
        private FavoritesDao mAsyncPersonDao;

        DeleteFavoritePersonAsyncTask(FavoritesDao dao){
            mAsyncPersonDao = dao;
        }

        @Override
        protected Void doInBackground(PeopleItem... peopleItems) {
            mAsyncPersonDao.deletePerson(peopleItems[0]);
            return null;
        }
    }

    private static class InsertFavoriteSpeciesAsyncTask extends AsyncTask<SpeciesItem, Void, Void> {
        private FavoritesDao mAsyncSpeciesDao;

        InsertFavoriteSpeciesAsyncTask(FavoritesDao dao){
            mAsyncSpeciesDao = dao;
        }

        @Override
        protected Void doInBackground(SpeciesItem... speciesItems) {
            mAsyncSpeciesDao.insertSpecies(speciesItems[0]);
            return null;
        }
    }

    private static class DeleteFavoriteSpeciesAsyncTask extends AsyncTask<SpeciesItem, Void, Void>{
        private FavoritesDao mAsyncSpeciesDao;

        DeleteFavoriteSpeciesAsyncTask(FavoritesDao dao){
            mAsyncSpeciesDao = dao;
        }

        @Override
        protected Void doInBackground(SpeciesItem... speciesItems) {
            mAsyncSpeciesDao.deleteSpecies(speciesItems[0]);
            return null;
        }
    }

    private static class InsertFavoriteStarshipAsyncTask extends AsyncTask<StarshipItem, Void, Void>{
        private FavoritesDao mAsyncStarshipDao;

        InsertFavoriteStarshipAsyncTask(FavoritesDao dao){
            mAsyncStarshipDao = dao;
        }

        @Override
        protected Void doInBackground(StarshipItem... starshipItems) {
            mAsyncStarshipDao.insertStarship(starshipItems[0]);
            return null;
        }
    }

    private static class DeleteFavoriteStarshipAsyncTask extends AsyncTask<StarshipItem, Void, Void> {
        private FavoritesDao mAsyncStarshipDao;

        DeleteFavoriteStarshipAsyncTask(FavoritesDao dao){
            mAsyncStarshipDao = dao;
        }

        @Override
        protected Void doInBackground(StarshipItem... starshipItems) {
            mAsyncStarshipDao.deleteStarship(starshipItems[0]);
            return null;
        }
    }

    private static class InsertFavoriteVehicleAsyncTask extends AsyncTask<VehicleItem, Void, Void> {
        private FavoritesDao mAsyncVehicleDao;

        InsertFavoriteVehicleAsyncTask(FavoritesDao dao){
            mAsyncVehicleDao = dao;
        }

        @Override
        protected Void doInBackground(VehicleItem... vehicleItems) {
            mAsyncVehicleDao.insertVehicle(vehicleItems[0]);
            return null;
        }
    }

    private static class DeleteFavoriteVehicleAsyncTask extends AsyncTask<VehicleItem, Void, Void> {
        private FavoritesDao mAsyncVehicleDao;

        DeleteFavoriteVehicleAsyncTask(FavoritesDao dao){
            mAsyncVehicleDao = dao;
        }

        @Override
        protected Void doInBackground(VehicleItem... vehicleItems) {
            mAsyncVehicleDao.deleteVehicle(vehicleItems[0]);
            return null;
        }
    }

}

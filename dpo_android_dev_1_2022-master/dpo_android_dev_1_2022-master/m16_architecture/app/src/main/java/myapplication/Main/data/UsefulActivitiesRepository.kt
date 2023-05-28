package myapplication.Main.data

import myapplication.Main.entity.UsefulActivity
import javax.inject.Inject


class UsefulActivitiesRepository @Inject constructor() {
    suspend fun getUsefulActivity(): UsefulActivity = ActivitySearchAPI.searchApi.getActivity()}




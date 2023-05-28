package myapplication.Main.domain

import myapplication.Main.data.UsefulActivitiesRepository
import myapplication.Main.entity.UsefulActivity
import javax.inject.Inject


class GetUsefulActivityUseCase @Inject constructor(val repository: UsefulActivitiesRepository) {
    suspend fun executeFun(): UsefulActivity {
        return repository.getUsefulActivity() }
}

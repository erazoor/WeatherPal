import fr.erazor.weatherpal.dao.WeatherCityDao
import fr.erazor.weatherpal.entity.WeatherCityEntity
import kotlinx.coroutines.flow.Flow

class WeatherCityRepository(private val wordDao: WeatherCityDao) {
    val allCities: Flow<List<WeatherCityEntity>> = wordDao.getWeatherCities()

    suspend fun insert(city: WeatherCityEntity) {
        wordDao.insert(city)
    }
}

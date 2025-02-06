package com.example.weatherappprojects.Fragment.Home

//import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherappprojects.data.AstroData
import com.example.weatherappprojects.data.CurrentLocation
import com.example.weatherappprojects.data.CurrentWeather
import com.example.weatherappprojects.data.Forecast
import com.example.weatherappprojects.data.WeatherData
import com.example.weatherappprojects.databinding.ItemContainerAstroBinding
import com.example.weatherappprojects.databinding.ItemContainerCurrentLocationBinding
import com.example.weatherappprojects.databinding.ItemContainerCurrentWeatherBinding
import com.example.weatherappprojects.databinding.ItemContainerForecastBinding

class WeatherDataAdapter(
    private val onLocationClicked: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val weatherData = mutableListOf<WeatherData>()

    companion object {
        const val INDEX_CURRENT_LOCATION = 0
        const val INDEX_CURRENT_WEATHER = 1
        const val INDEX_ASTRO = 2
        const val INDEX_FORECAST = 3
    }


    inner class CurrentLocationViewHolder(
        private val binding: ItemContainerCurrentLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentLocation: CurrentLocation) {
            with(binding) {
                textCurrentDate.text = currentLocation.date
                textCurrentLocation.text = currentLocation.Location
                imageCurrentLocation.setOnClickListener { onLocationClicked() }
                textCurrentLocation.setOnClickListener { onLocationClicked() }
            }
        }
    }

    fun setCurrentLocation(currentLocation: CurrentLocation) {
        if (weatherData.isEmpty()) {
            weatherData.add(INDEX_CURRENT_LOCATION, currentLocation)
            notifyItemInserted(INDEX_CURRENT_LOCATION)
        } else {
            weatherData[INDEX_CURRENT_LOCATION] = currentLocation
            notifyItemChanged(INDEX_CURRENT_LOCATION)
        }
    }

    fun setCurrentWeather(currentWeather: CurrentWeather) {
        if (weatherData.getOrNull(INDEX_CURRENT_WEATHER) != null) {
            weatherData[INDEX_CURRENT_WEATHER] = currentWeather
            notifyItemChanged(INDEX_CURRENT_WEATHER)
        } else {
            weatherData.add(INDEX_CURRENT_WEATHER, currentWeather)
            notifyItemInserted(INDEX_CURRENT_WEATHER)
        }
    }

    fun setForecastData(forecast: List<Forecast>) {
        weatherData.removeAll { it is Forecast }
        notifyItemRangeRemoved(INDEX_FORECAST, weatherData.size)

        if (weatherData.size >= INDEX_FORECAST) {
            weatherData.addAll(INDEX_FORECAST, forecast)
        } else {
            weatherData.addAll(forecast)
        }
        notifyItemRangeInserted(INDEX_FORECAST, forecast.size)
    }

    fun setAstroData(astro: AstroData) {
        if (weatherData.size > INDEX_ASTRO && weatherData[INDEX_ASTRO] is AstroData) {
            weatherData[INDEX_ASTRO] = astro
            notifyItemChanged(INDEX_ASTRO)
        } else {
            weatherData.add(INDEX_ASTRO, astro)
            notifyItemInserted(INDEX_ASTRO)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            INDEX_CURRENT_LOCATION -> CurrentLocationViewHolder(
                ItemContainerCurrentLocationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            INDEX_FORECAST -> ForecastViewHolder(
                ItemContainerForecastBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            INDEX_ASTRO -> AstroViewHolder(
                ItemContainerAstroBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> CurrentWeatherViewHolder(
                ItemContainerCurrentWeatherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CurrentLocationViewHolder -> holder.bind(weatherData[position] as CurrentLocation)
            is CurrentWeatherViewHolder -> holder.bind(weatherData[position] as CurrentWeather)
            is ForecastViewHolder -> holder.bind(weatherData[position] as Forecast)
            is AstroViewHolder -> holder.bind(weatherData[position] as AstroData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (weatherData[position]) {
            is CurrentLocation -> INDEX_CURRENT_LOCATION
            is CurrentWeather -> INDEX_CURRENT_WEATHER
            is AstroData -> INDEX_ASTRO
            is Forecast -> INDEX_FORECAST
        }
    }

    inner class CurrentWeatherViewHolder(
        private val binding: ItemContainerCurrentWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentWeather: CurrentWeather) {
            with(binding) {
                imageIcon.load("https:${currentWeather.icon}") {
                    crossfade(true)
                }
                textTemperature.text = String.format("%s\u00B0C", currentWeather.temperature)
                textWind.text = String.format("%s km/h", currentWeather.wind)
                textHumidity.text = String.format("%s%%", currentWeather.humidity)
                textChanceOfRain.text = String.format("%s%%", currentWeather.chaneOfRain)
            }
        }
    }

    inner class ForecastViewHolder(
        private val binding: ItemContainerForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(forecast: Forecast) {
            with(binding) {
                textTime.text = forecast.time
                textTemperature.text = String.format("%s\u00B0C", forecast.temperature)
                textFeelsLikeTemperature.text =
                    String.format("%s\u00B0C", forecast.feelsLikeTemperature)
                imageIcon.load("https:${forecast.icon}") {
                    crossfade(true)
                }
            }
        }
    }

    inner class AstroViewHolder(
        private val binding: ItemContainerAstroBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(astro: AstroData) {
            with(binding) {
                sunsetTime.text = astro.sunset
                sunriseTime.text = astro.sunrise
            }
        }
    }
}

//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(data:List<WeatherData>){
//        weatherData.clear()
//        weatherData.addAll(data)
//        notifyDataSetChanged()
//    }
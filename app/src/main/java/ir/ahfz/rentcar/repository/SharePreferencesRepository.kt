package ir.ahfz.rentcar.repository

import android.content.Context
import android.content.SharedPreferences
import ir.ahfz.rentcar.MyApplication

class SharePreferencesRepository(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("", 0)

    /**
     * Gets server url
     */
    fun getServerURL(): String = sharedPreferences.getString("server_url", MyApplication.base_url)!!


    /**
     * Sets server url
     */
    fun setServerUrl(url: String) {
        if (url.isNotEmpty() || url.isNotBlank()) {
            var newUrl = url
            if (!newUrl.startsWith("http://"))
                newUrl = "http://$newUrl"
            sharedPreferences.edit().putString("server_url", newUrl).apply()
        }
    }

}
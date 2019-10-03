package ir.ahfz.rentcar.io.network.webservice

import ir.ahfz.rentcar.io.network.model.*
import retrofit2.Call
import retrofit2.http.GET

interface PublicAccessWebservice {

    /**
     * API to retrieve cities
     */
    @GET("/api/cities")
    fun getCities(): Call<CityResponse>

    /**
     *  API to retrieve makes
     */
    @GET("/api/makes")
    fun getMakes(): Call<MakeResponse>

    /**
     * API to retrieve cars
     */
    @GET("/api/cars")
    fun getCars(): Call<CarResponse>

    /**
     * API to retrieve fuels
     */
    @GET("/api/fuels")
    fun getFuels(): Call<FuelResponse>

    /**
     * API to retrieve extras
     */
    @GET("/api/extras")
    fun getExtras(): Call<ExtraResponse>

    /**
     * API to retrieve car classes
     */
    @GET("/api/classes")
    fun getClasses(): Call<ClassResponse>

    /**
     * API to retrieve branches
     */
    @GET("/api/branches")
    fun getBranches(): Call<BranchResponse>

    /**
     * API to retrieve models
     */
    @GET("/api/models")
    fun getModels(): Call<ModelResponse>

    /**
     * API to retrieve colors
     */
    @GET("/api/colors")
    fun getColors(): Call<ColorResponse>

}
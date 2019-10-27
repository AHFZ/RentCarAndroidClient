package ir.ahfz.rentcar.io.network.webservice

import ir.ahfz.rentcar.io.network.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface AdminAccessWebservice {

    /**
     * API to retrieve all reservations
     */
    @GET("/admin/api/reservations")
    fun fetchReservations(): Call<BaseResponse>

    /**
     * API to confirm reservation was received
     */
    @GET("/admin/api/reservations/confirm")
    fun confirmReservation(): Call<BaseResponse>

    /**
     * API to confirm that ic_car was delivered to customer
     */
    @GET("/admin/api/reservations/deliver")
    fun confirmReservationDelivery(): Call<BaseResponse>

    /**
     * API to confirm that ic_car was returned and set reservation as completed
     */
    @GET("/admin/api/reservations/returned")
    fun confirmReservationCompletelyReturn()

    /**
     *  API to add Make
     */
    @POST("/api/makes")
    fun addMake(): Call<BaseResponse>

    /**
     *  API to add fuel
     */
    @POST("/api/fuels")
    fun addFuel(): Call<BaseResponse>

    /**
     *  API to add Extra
     */
    @POST("/api/extras")
    fun addExtra(): Call<BaseResponse>

    /**
     * API to add cities
     */
    @POST("/api/cities")
    fun addCity(): Call<BaseResponse>

    /**
     * API to add classes
     */
    @POST("/api/classes")
    fun addClass(): Call<BaseResponse>

    /**
     *  API to color
     */
    @POST("/api/colors")
    fun addColor(): Call<BaseResponse>

    /**
     * API to add branches
     */
    @POST("/api/branches")
    fun addBranch(): Call<BaseResponse>

    /**
     * API to add models
     */
    @POST("/api/models")
    fun addModel(): Call<BaseResponse>

    /**
     * API to add ic_car
     */
    @POST("/api/cars")
    fun addCar(): Call<BaseResponse>
}
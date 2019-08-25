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
     * API to confirm that car was delivered to customer
     */
    @GET("/admin/api/reservations/deliver")
    fun confirmReservationDelivery(): Call<BaseResponse>

    /**
     * API to confirm that car was returned and set reservation as completed
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
    @POST("/api/makes")
    fun addFuel(): Call<BaseResponse>

    /**
     *  API to add Extra
     */
    @POST("/api/makes")
    fun addExtra(): Call<BaseResponse>

    /**
     * API to add colors
     */
    fun adCity(): Call<BaseResponse>

    @POST("/api/makes")
    fun addClass(): Call<BaseResponse>

    /**
     *  API to color
     */
    @POST("/api/makes")
    fun addColor(): Call<BaseResponse>

    /**
     * API to add branche
     */
    @POST("/api/makes")
    fun addBranch(): Call<BaseResponse>

    /**
     * API to models under available make
     */
    @POST("/api/makes")
    fun addModel(): Call<BaseResponse>

    /**
     * API to add car
     */
    @POST("/api/makes")
    fun addCar(): Call<BaseResponse>
}
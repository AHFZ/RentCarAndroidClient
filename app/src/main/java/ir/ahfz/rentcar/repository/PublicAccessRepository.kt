package ir.ahfz.rentcar.repository

import ir.ahfz.rentcar.io.model.*
import ir.ahfz.rentcar.io.network.webservice.PublicAccessWebservice
import retrofit2.Response
import retrofit2.awaitResponse

class PublicAccessRepository(
    private val publicAccessWebservice: PublicAccessWebservice
) {

    /**
     * API to retrieve cities
     */
    suspend fun getCities(): Response<CityResponse> {
        return publicAccessWebservice.getCities().awaitResponse()

    }

    /**
     *  API to retrieve makes
     */
    suspend fun getMakes(): Response<MakeResponse> {
        return publicAccessWebservice.getMakes().awaitResponse()

    }

    /**
     * API to retrieve cars
     */
    suspend fun getCars(): Response<CarResponse> {
        return publicAccessWebservice.getCars().awaitResponse()

    }

    /**
     * API to retrieve fuels
     */
    suspend fun getFuels(): Response<FuelResponse> {
        return publicAccessWebservice.getFuels().awaitResponse()

    }

    /**
     * API to retrieve extras
     */
    suspend fun getExtras(): Response<ExtraResponse> {
        return publicAccessWebservice.getExtras().awaitResponse()

    }

    /**
     * API to retrieve ic_car classes
     */
    suspend fun getClasses(): Response<ClassResponse> {
        return publicAccessWebservice.getClasses().awaitResponse()

    }

    /**
     * API to retrieve branches
     */
    suspend fun getBranches(): Response<BranchResponse> {
        return publicAccessWebservice.getBranches().awaitResponse()

    }

    /**
     * API to retrieve models
     */
    suspend fun getModels(): Response<ModelResponse> {
        return publicAccessWebservice.getModels().awaitResponse()

    }

    /**
     * API to retrieve colors
     */
    suspend fun getColors(): Response<ColorResponse> {
        return publicAccessWebservice.getColors().awaitResponse()
    }
}
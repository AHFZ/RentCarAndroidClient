package ir.ahfz.rentcar.ui.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.ahfz.rentcar.io.model.ExtraResponse
import ir.ahfz.rentcar.repository.PublicAccessRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookCarViewModel(private val publicAccessRepository: PublicAccessRepository) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorLiveData.postValue(throwable.message)
    }
    val errorLiveData = MutableLiveData<String?>()
    val extraResponseLiveData = MutableLiveData<ExtraResponse>()

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val extras = publicAccessRepository.getExtras()
            if (extras.isSuccessful)
                extraResponseLiveData.postValue(extras.body())
        }
    }

}

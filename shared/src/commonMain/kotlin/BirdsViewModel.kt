import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.BirdClass


data class BirdsUIState(val list: List<BirdClass> = emptyList(),
                        var selectedCategory: String? = null) {
    val categories = list.map { it.category }
        .toSet()
    val selectedImages = list.filter { it.category == selectedCategory }
}

class BirdsViewModel : ViewModel() {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    private val _birdUIState: MutableStateFlow<BirdsUIState> = MutableStateFlow(BirdsUIState())
    val birdUIState = _birdUIState.asStateFlow()

    init {
        updateImages()
    }

    private suspend fun getImages(): List<BirdClass> {
        val images = httpClient.get("https://sebi.io/demo-image-api/pictures.json")
            .body<List<BirdClass>>()
        return images
    }

    override fun onCleared() {
        httpClient.close()
        super.onCleared()
    }

    fun selectCategory(category: String) {
        _birdUIState.update { it.copy(selectedCategory = category) }
    }

    fun updateImages() {
        viewModelScope.launch {
            val images = getImages()
            _birdUIState.update {
                it.copy(list = images)
            }
        }
    }

}
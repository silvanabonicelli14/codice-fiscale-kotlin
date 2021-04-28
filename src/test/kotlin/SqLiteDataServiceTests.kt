import com.cgm.codicefiscale.services.SqLiteDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SqLiteDataServiceTests {
    private var sut = SqLiteDataService()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        val result = sut.loadCountryCode()
        Assertions.assertEquals(7905, result.size)
    }
}
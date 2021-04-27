import com.cgm.codicefiscale.services.SqLiteDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SqLiteDataServiceTests {
    var sut = SqLiteDataService()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        var result = sut.loadData()

        Assertions.assertEquals(2, result.size)
    }
}
import com.cgm.codicefiscale.FiscalCodeCalculator
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.services.CsvDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CsvDataServiceTests {
    var csvDataService = CsvDataService()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        var result = csvDataService.loadData()

        Assertions.assertEquals(1, result.size)
    }
}
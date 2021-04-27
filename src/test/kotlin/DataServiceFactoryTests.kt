import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.services.DataServiceFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DataServiceFactoryTests {
    var sut = DataServiceFactory()

    @Test
    fun `Get Data Service from system environment  happy path`() {
        val result = sut.getDataServiceFromEnvironment()
        Assertions.assertTrue(result is CsvDataService)
    }
    @Test
    fun `Get Data Service from system environment not exists return defult value csv`() {
        val result = sut.getDataServiceFromEnvironment("BLABLA")
        Assertions.assertTrue(result is CsvDataService)
    }

}